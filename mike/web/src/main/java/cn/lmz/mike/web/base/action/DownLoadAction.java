package cn.lmz.mike.web.base.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import cn.lmz.mike.common.log.O;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller()
@Scope("prototype")
public class DownLoadAction{
    //上传文件存放路径   
	private static final long serialVersionUID = 1L;
	
    //下载文件名字  
    private String name;
	private String fileName;//下载文件命名
	

	public InputStream getInputStream() throws Exception{
		 try{
		   InputStream in=ServletActionContext.getServletContext().getResourceAsStream(name);
		   if(in==null){
			   O.error("文件不存在!");
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
