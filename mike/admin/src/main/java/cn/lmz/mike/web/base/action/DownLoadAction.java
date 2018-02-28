package cn.lmz.mike.web.base.action;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;


@Controller()
@Scope("prototype")
public class DownLoadAction{

	private static final Logger log = LoggerFactory.getLogger(DownLoadAction.class);
    //上传文件存放路径   
	private static final long serialVersionUID = 1L;
	
    //下载文件名字  
    private String name;
	private String fileName;//下载文件命名
	

	public InputStream getInputStream() throws Exception{
		 try{
		   InputStream in=ServletActionContext.getServletContext().getResourceAsStream(name);
		   if(in==null){
			   log.error("文件不存在!");
		   }
		   return in;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	}
	
	public String getFileName() {
		
		try {
			fileName=name;
			int i=fileName.lastIndexOf("/");
			fileName=fileName.substring(i+1);
			fileName=new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		return fileName;
	}	
	
    public String execute() throws Exception {
    	   return "success";
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
    

}
