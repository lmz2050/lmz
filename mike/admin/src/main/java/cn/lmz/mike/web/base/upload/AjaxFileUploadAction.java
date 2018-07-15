package cn.lmz.mike.web.base.upload;

import cn.lmz.mike.web.base.action.BasicAction;
import cn.lmz.mike.web.base.util.WebSV;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;

@Controller()
@Scope("prototype")
public class AjaxFileUploadAction extends BasicAction {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat formatterd = new SimpleDateFormat("yyyyMMdd");
    //上传文件存放路径
    //上传文件存放路径
    private final static String UPLOADDIR = WebSV.getFileUploadPath();

    private File file;            //文件
    private String fileFileName;  //文件名
    private String filePath;      //文件路径
    private InputStream inputStream;

    /**
     * 图片上传
     *
     * @return
     */
    public String execute() {
        String path = UPLOADDIR;
        File file = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            if (this.file != null) {
                File f = this.getFile();
                String fileName = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式随即命名
                String name = fileName+ fileFileName.substring(fileFileName.lastIndexOf(".")); // 保存在硬盘中的文件名

                FileInputStream inputStream = new FileInputStream(f);
                FileOutputStream outputStream = new FileOutputStream(path+ "\\" + name);
                byte[] buf = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, length);
                }
                inputStream.close();
                outputStream.flush();
                //文件保存的完整路径  比如：D:\tomcat6\webapps\eserver\\upload\a0be14a1-f99e-4239-b54c-b37c3083134a.png
                filePath = path+"\\"+name;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 用于图片页面显示
     *
     * @return
     */
    public String readImg() {
        try {
            inputStream = new FileInputStream(new File(getRequest().getParameter("path")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    /**
     * 图片下载
     * @return
     */
    public String download() {
        String path = filePath;
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            //清空response
            response.reset();
            //设置response的Header
            String filenameString = new String(filename.getBytes("gbk"),"iso-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + filenameString);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private static final Logger log = LoggerFactory.getLogger(AjaxFileUploadAction.class);
    private static final long serialVersionUID = 1L;
}
