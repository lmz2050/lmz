package cn.lmz.mike.web.base.action;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.web.base.util.WebSV;
import cn.lmz.mike.web.base.util.WebU;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller()
@Scope("prototype")
public class DownAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String name;
    private String fileName;
    private transient HttpServletRequest request;
    private transient HttpServletResponse response;
    private static final String UPLOADDIR = WebSV.getFileUploadPath();
    private Logger logger = LoggerFactory.getLogger(DownAction.class);

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public HttpServletRequest getRequest() {
        return request;
    }
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * 文件下载
     * @param name 要下载的文件名称
     * @throws IOException
     */
    public String downLoadFile(){
        // 下载文件所处于的目录
        WebU.upDCount(1);
        String fileFullName = UPLOADDIR + File.separator + name;
        File downloadFile = new File(fileFullName);
        try {
            downloadFileRanges(downloadFile);
            //downloadFileRanges2(downloadFile);
            //fileDownload(downloadFile);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
        WebU.upDCount(-1);
        return null;
    }

    /**
     * 文件下载(支持断点续传)
     * @param downloadFile
     * @throws IOException
     */
    private void downloadFileRanges(File downloadFile) throws IOException {
        // 要下载的文件大小
        long fileLength = downloadFile.length();
        // 已下载的文件大小
        long pastLength = 0;
        // 是否快车下载，否则为迅雷或其他
        boolean isFlashGet = true;
        // 用于记录需要下载的结束字节数（迅雷或其他下载）
        long lenEnd = 0;
        // 用于记录客户端要求下载的数据范围字串
        String rangeBytes = request.getHeader("Range");
        // 用于随机读取写入文件
        RandomAccessFile raf = null;
        OutputStream os = null;
        OutputStream outPut = null;
        byte b[] = new byte[1024];

        // 如果客户端下载请求中包含了范围
        if (null != rangeBytes)
        {
            // 返回码 206
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            rangeBytes = request.getHeader("Range").replaceAll("bytes=", "");
            // 判断 Range 字串模式
            if (rangeBytes.indexOf('-') == rangeBytes.length() - 1)
            {
                // 无结束字节数，为快车
                isFlashGet = true;
                rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf('-'));
                pastLength = Long.parseLong(rangeBytes.trim());
            }
            else
            {
                // 迅雷下载
                isFlashGet = false;
                String startBytes = rangeBytes.substring(0,
                        rangeBytes.indexOf('-'));
                String endBytes = rangeBytes.substring(
                        rangeBytes.indexOf('-') + 1, rangeBytes.length());

                // 已下载文件段
                pastLength = Long.parseLong(startBytes.trim());

                // 还需下载的文件字节数(从已下载文件段开始)
                lenEnd = Long.parseLong(endBytes);
            }
        }

        // 通知客户端允许断点续传，响应格式为：Accept-Ranges: bytes
        response.setHeader("Accept-Ranges", "bytes");
        // response.reset();

        // 如果为第一次下载，则状态默认为 200，响应格式为： HTTP/1.1 200 ok
        if (0 != pastLength)
        {
            // 内容范围字串
            String contentRange = "";

            // 响应格式
            // Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]||[文件的总大小]
            if (isFlashGet)
            {
                contentRange = new StringBuffer("bytes")
                        .append(new Long(pastLength).toString()).append("-")
                        .append(new Long(fileLength - 1).toString())
                        .append("/").append(new Long(fileLength).toString())
                        .toString();
            }
            else
            {
                contentRange = new StringBuffer(rangeBytes).append("/")
                        .append(new Long(fileLength).toString()).toString();
            }
            response.setHeader("Content-Range", contentRange);
        }

        //String fileName = getDownloadChineseFileName(name);
        if(MC.string.isEmpty(fileName)){
            fileName =downloadFile.getName();
        }
        response.setHeader("Content-Disposition",
                "attachment;filename=" + fileName + "");

        // 响应的格式是:
        response.setContentType("application/octet-stream");

        response.addHeader("Content-Length", String.valueOf(fileLength));
        try
        {
            os = response.getOutputStream();
            outPut = new BufferedOutputStream(os);
            raf = new RandomAccessFile(downloadFile, "r");
            // 跳过已下载字节
            raf.seek(pastLength);
            if (isFlashGet)
            {
                // 快车等
                int n = 0;
                while ((n = raf.read(b, 0, 1024)) != -1)
                {
                    outPut.write(b, 0, n);
                }
            }
            else
            {
                // 迅雷等
                while (raf.getFilePointer() < lenEnd)
                {
                    outPut.write(raf.read());
                }
            }
            outPut.flush();
        }
        catch (IOException e)
        {
            /**
             * 在写数据的时候 对于 ClientAbortException 之类的异常
             * 是因为客户端取消了下载，而服务器端继续向浏览器写入数据时， 抛出这个异常，这个是正常的。 尤其是对于迅雷这种吸血的客户端软件。
             * 明明已经有一个线程在读取 bytes=1275856879-1275877358，
             * 如果短时间内没有读取完毕，迅雷会再启第二个、第三个。。。线程来读取相同的字节段， 直到有一个线程读取完毕，迅雷会 KILL
             * 掉其他正在下载同一字节段的线程， 强行中止字节读出，造成服务器抛 ClientAbortException。
             * 所以，我们忽略这种异常
             */
        }
        finally
        {
            if(outPut != null)
            {
                //outPut.close();
            }
            if(raf != null)
            {
                raf.close();
            }
        }
    }

    /**
     * 文件下载(支持断点续传)
     * @param file
     * @return
     */
    private void downloadFileRanges2(File file) {
        if (!file.exists())
        {
            // 文件不存在
            return;
        }
        else
        {
            FileInputStream fis = null;
            OutputStream outPut = null;
            try
            {
                fis = new FileInputStream(file);
                response.setHeader("Accept-Ranges", "bytes");
                // 下载开始字节
                long downBegin = 0;
                // 文件大小
                long fileSize = 0;
                fileSize = file.length();
                // 如果是第一次下,还没有断点续传,状态是默认的 200,无需显式设置
                // 客户端请求的下载的文件块的开始字节
                if (request.getHeader("Range") != null)
                {
                    // 如果是下载文件的范围而不是全部,向客户端声明支持并开始文件块下载
                    response.setStatus(javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT);
                    // 从请求中得到开始的字节
                    downBegin = Long.parseLong(request.getHeader("Range")
                            .replaceAll("bytes=", "").replaceAll("-", ""));
                }
                // 下载的文件(或块)长度
                response.setHeader("Content-Length", new Long(fileSize
                        - downBegin).toString());
                if (downBegin != 0)
                {
                    // 不是从最开始下载,
                    response.setHeader("Content-Range", "bytes "
                            + new Long(downBegin).toString() + "-"
                            + new Long(fileSize - 1).toString() + "/"
                            + new Long(fileSize).toString());
                }
                // 响应的格式是:
                response.setContentType("application/octet-stream");
                // 为客户端下载指定默认的下载文件名称
                //String dfileName = getDownloadChineseFileName(fileName);
                response.reset();
                response.addHeader("Content-Disposition", "attachment;filename="
                        + java.net.URLEncoder.encode(fileName, "utf-8"));
                response.addHeader("Content-Length", "" + file.length());

                fis.skip(downBegin);
                byte[] b = new byte[1024];
                int len;
                outPut = response.getOutputStream();
                while ((len = fis.read(b)) != -1)
                {
                    outPut.write(b, 0, len);
                    //response.flushBuffer();
                }
                outPut.flush();
                fis.close();
            }
            catch (FileNotFoundException e)
            {
                System.out.println("error");
            }
            catch (IOException e)
            {
                /**
                 * 在写数据的时候
                 * 对于 ClientAbortException 之类的异常
                 * 是因为客户端取消了下载，而服务器端继续向浏览器写入数据时，
                 * 抛出这个异常，这个是正常的。
                 * 尤其是对于迅雷这种吸血的客户端软件。
                 * 明明已经有一个线程在读取 bytes=1275856879-1275877358，
                 * 如果短时间内没有读取完毕，迅雷会再启第二个、第三个。。。线程来读取相同的字节段，
                 * 直到有一个线程读取完毕，迅雷会 KILL 掉其他正在下载同一字节段的线程，
                 * 强行中止字节读出，造成服务器抛 ClientAbortException。
                 * 所以，我们忽略这种异常
                 */
            }
            finally
            {
                if (fis != null)
                {
                    try {
                        fis.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                if(outPut != null)
                {
                    try
                    {
                        outPut.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    /**
     * 如果下载文件名为中文，进行字符编码转换
     * @param paramName
     * @return downloadChineseFileName
     */
    private String getDownloadChineseFileName(String paramName)
    {
        String downloadChineseFileName = "";
        try
        {
            downloadChineseFileName = new String(paramName.getBytes("GBK"), "ISO8859-1");
        }
        catch (UnsupportedEncodingException e)
        {
            //e.printStackTrace();
        }
        return downloadChineseFileName;
    }

    /**
     * 文件下载(不支持断点续传)
     * @param file
     * @throws IOException
     */
    private void fileDownload(File file)
            throws IOException
    {
        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();

        // 清空response
        response.reset();
        response.addHeader("Content-Disposition", "attachment;filename="
                + java.net.URLEncoder.encode(fileName, "utf-8"));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        os.write(buffer);
        os.flush();
        os.close();
    }

    @Override
    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    @Override
    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }
}