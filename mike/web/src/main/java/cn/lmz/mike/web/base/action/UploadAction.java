package cn.lmz.mike.web.base.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.lmz.mike.common.date.DateU;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.web.base.util.WebSV;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
  
//文件上传Action 
@Controller()
@Scope("prototype")
public class UploadAction extends ActionSupport {   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat formatterd = new SimpleDateFormat("yyyyMMdd");
	//上传文件存放路径   
	//上传文件存放路径   
    private final static String UPLOADDIR = WebSV.getFileUploadPath();
    //上传文件集合   
    private File file;   
    //上传文件名集合   
    private String fileFileName;   
    //上传文件内容类型集合   
    private String fileContentType;
    
    private String tagid;
    
    private String ipath;
    
    private String error;
  
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
  
    public String getFileContentType() {   
        return fileContentType;   
    }   
  
    public void setFileContentType(String fileContentType) {   
        this.fileContentType = fileContentType;   
    }   

    public String getTagid() {
		return tagid;
	}

	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	
	public String getIpath() {
		return ipath;
	}

	public void setIpath(String ipath) {
		this.ipath = ipath;
	}

	public String execute() throws Exception {   

    	if(file==null){return INPUT;}
            uploadFile();
            error="success";
        return "success";  
    } 

  
    //执行上传功能   
	private void uploadFile() throws FileNotFoundException, IOException {   
        try {   
            InputStream in = new FileInputStream(file);
            
            if(null==ipath||ipath==""){ipath="";}else{ipath+="/";}
            String filePath="/upfile/"+ipath+formatterd.format(new Date())+"/";
            String updir = WebSV.getFileUploadPath()+filePath;
            //String dir = ServletActionContext.getServletContext().getRealPath(updir);
            
            File dirf = new File(updir);
            if( (!dirf.exists()) && (!dirf.isFile())){//如果dirfile不是一个文件【不是文件就是路径】并且不存在   
                dirf.mkdirs();      
            }

            fileFileName=this.getUpFileName(fileFileName);//auto name by time

            File uploadFile = new File(updir, fileFileName);
            fileFileName=filePath+fileFileName;
            O.pn("update file:"+fileFileName +" to "+uploadFile.getAbsolutePath());
            
            OutputStream out = new FileOutputStream(uploadFile);   
            byte[] buffer = new byte[1024 * 1024];   
            int length;   
            while ((length = in.read(buffer)) > 0) {   
                out.write(buffer, 0, length);   
            }   
  
            in.close();   
            out.close();   
        } catch (FileNotFoundException ex) {   
            ex.printStackTrace();   
        } catch (IOException ex) {   
            ex.printStackTrace();   
        }   
    } 
    
    private String getUpFileName(String name){    	
        return DateU.getCurrentDate(DateU.FORMAT13)+"."+this.getExt(name);
    }
    private String getExt(String name){
        int i=name.lastIndexOf(".");//
        String ext=name.substring(i+1);//
        return ext;
    }

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}  
