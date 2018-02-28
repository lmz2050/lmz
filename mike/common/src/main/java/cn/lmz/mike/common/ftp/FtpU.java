package cn.lmz.mike.common.ftp;

import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.common.sec.SecurityU;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * =================================================================
 * 版权所有 2010-2020 佰仟金融服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * =================================================================
 * @ClassName: FtpUtil 
 * @Description: FTP上传工具类
 * @author aijun.luo
 * @date 2016年7月28日 下午2:36:30  
 */
public class FtpU extends FTPClient{

	private static final Logger log = LoggerFactory.getLogger(FtpU.class);

    public boolean openServer(String ip,String user,String pass,int port) throws Exception{           
    	boolean result = false;
    	try {
        	log.info("openServer:"+ip+","+user+",******,"+port);
			this.connect(ip, port);
			this.login(user,pass);
			this.setFileType(FTPClient.BINARY_FILE_TYPE);
	        int reply = this.getReplyCode();
	        if (!FTPReply.isPositiveCompletion(reply)) {
	        	this.disconnect();
	            return result;
	        }
	        result = true; 
	        this.enterLocalPassiveMode();
	        log.info("connect:"+result);
		} catch (SocketException e) {
			log.error(e.getMessage(),e);
			throw e;
		} catch (IOException e) {
			log.error(e.getMessage());
			throw e;
		}
    	
	    return result;      
    } 
    
	public boolean open(String ftp) throws Exception{
		ftp  = SecurityU.getDeValue(ftp);
		String[] ftpinfo = StrU.getArray(ftp);
		
		int port = Integer.valueOf(ftpinfo[3]);
		
		return this.openServer(ftpinfo[0], ftpinfo[1], ftpinfo[2], port);
	}

    public boolean deleteFile(String pathname) throws IOException
    {
		try{
			return super.deleteFile(pathname);
		}catch(IOException e){
			log.error("deleteFile 异常："+e.getMessage(),e);
			throw e;
		}
    }
	
    
    

    public boolean upload(String fileName, String newName) throws Exception{
    	boolean result = false;
    	log.info("---upload---"+fileName+"-->"+newName);
    	InputStream is = null;
        try{     
            File file_in = new File(fileName);//打开本地待长传的文件
            if(!file_in.exists()){      
                throw new Exception("此文件或文件夹[" + file_in.getName() + "]有误或不存在!");      
            }
            
            String directory = newName.substring(0, newName.lastIndexOf("/") + 1);
            
            if(directory!=null&&directory.trim().length()>0&&!this.changeWorkingDirectory(directory)){
    			this.CreateDirecroty(directory);
    		}
            

            is  = new FileInputStream(file_in);
                        
            result = storeFile(newName, is);

            log.info("upload completed!"+result);
            return true;
        }catch(Exception e){       
            log.error(e.getMessage());      
            throw e; 
        }finally{      
            try{      
                if(is != null){      
                    is.close();      
                }          
            }catch(IOException e){      
                e.printStackTrace();      
           }       
        }      
    }
    
    
    
    
    
    
    

    /**
     * 上传文件
     * @param file 上传的文件或文件夹
     * @throws IOException 
     * @throws Exception
     */
    public boolean upload(String path,String fileName,InputStream file) throws IOException{
    	log.info("---开始ftp文件上传---"+path+","+fileName);
    	boolean result=false;
    	try {
    		
    		if(!this.changeWorkingDirectory(path)){
    			this.CreateDirecroty(path);
    		}
			/*
			//当年文件目录
			ftp.makeDirectory(getTimeStr("year"));
			ftp.changeWorkingDirectory(getTimeStr("year"));
			//当月文件目录
			ftp.makeDirectory(getTimeStr("mon"));
			ftp.changeWorkingDirectory(getTimeStr("mon"));
			//当天文件目录
			ftp.makeDirectory(getTimeStr("day"));
			ftp.changeWorkingDirectory(getTimeStr("day"));
			*/
			
    		result = storeFile(fileName, file);
		} catch (IOException e) {
			log.info("---IOException---upload---ftp上传失败---"+path+","+fileName+":"+e.getMessage());
			throw e;
		}
    	log.info(result+"");
    	return result;
       
    }
    
    public boolean CreateDirecroty(String remote) throws IOException {
    	log.debug("远程文件夹地址：" + remote);
        boolean success = true;
        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/")
                && !changeWorkingDirectory(new String(directory))) {

            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            while (true) {
                String subDirectory = new String(remote.substring(start, end));
                if (!changeWorkingDirectory(subDirectory)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                    	log.error("---- ftp创建目录失败 -----");
                        success = false;
                        return success;
                    }
                }
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }
    
    
    
    public static void main(String[] args){
    	Date date = new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/HH");
    	String dataStr = format.format(date);
    	
    	System.out.println(dataStr.substring(0, 4));
    	System.out.println(dataStr.substring(4, 6));
    	System.out.println(dataStr.substring(6, 8));
    	
    	String filename = "E:\\123.jpg";
    	String newname = "/asd/1234.jpg";
		try {

			O.changeLogLev("trace");
			String host="10.45.1.17";
			String username="ftp31";
			String password="admin@c2sbo3";
			int port=21;
			
			FtpU f = new FtpU();
			f.openServer(host,username,password,port);
			f.upload(filename, newname);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

}
