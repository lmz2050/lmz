package cn.lmz.mike.common.io;

import cn.lmz.mike.common.V;
import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.intl.chardet.nsDetector;


public class FileU {

	private static Log log =LogFactory.getLog(FileU.class);
	
	private String name;
	private boolean isAdd=false;
	private boolean isBr=false;
	private static String charset="GBK";
	private static char linebr=9;

	public File create(String name){
		this.name = name;
		File file = new File(this.name);
		if(!file.exists()){
			file.mkdirs();
		}
		return file;
	}
		
	public void delete(String name){
		File file = new File(name);
		if(file.exists()){
			file.delete();
		}
	}
	
	public void move(String name,String path){
		File file = new File(name);
		if(file.exists()){
			file.renameTo(new File(path,file.getName()));
		}
		delete(name);
	}
	
	public void moveAll(String path1,String path){
		File file = new File(path1);
		if(file.exists()&&file.isDirectory()){
			File[] files = file.listFiles();
			for(int i=0;i<files.length;i++){
				File filei = files[i];
				move(filei.getAbsolutePath(),path);
			}
		}
	}
	
	public void write(List<String> list) throws IOException{
		OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(name,this.isAdd),charset);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				if(this.isBr){
					w.write(list.get(i)+ V.LINEBR);
				}else{
					w.write(list.get(i));
				}
			}
		}
		w.flush();
		w.close();
	}
	public static void write(File file,String str) throws IOException{
		FileUtils.writeStringToFile(file,str,charset);
	}
	public List<String> read(String cfgFile) throws IOException{
        File fileF=new File(cfgFile);
        String fileEncode = FileU.getFileEncode(cfgFile);     
        return FileUtils.readLines(fileF, fileEncode);
	}
	public static boolean readFile(StringBuffer buffer, String fileSrc) throws IOException {
		File fileF=new File(fileSrc);
		String fileEncode = FileU.getFileEncode(fileSrc);  
        FileInputStream in = null;
        try {
        	in = FileUtils.openInputStream(fileF);
            String str = IOUtils.toString(in, Charsets.toCharset(fileEncode));
            buffer.append(str);
        	} finally { IOUtils.closeQuietly(in);	}
        return true;
	}
	
	public static String getFileEncode(String filePath) {
        String charsetName = null;
        try {
            File file = new File(filePath);
            CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
            detector.add(new ParsingDetector(false));
            detector.add(JChardetFacade.getInstance());
            detector.add(ASCIIDetector.getInstance());
            detector.add(UnicodeDetector.getInstance());
            java.nio.charset.Charset charset = null;
            charset = detector.detectCodepage(file.toURI().toURL());
            if (charset != null) {
                charsetName = charset.name();
            } else {
                charsetName = "UTF-8";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return charsetName;
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	public boolean isBr() {
		return isBr;
	}

	public void setBr(boolean isBr) {
		this.isBr = isBr;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}


	public static String getPath(String rpath){
		return FileU.class.getClassLoader().getResource(rpath).getPath();
	}
	public static String getClassPath(){
		return FileU.class.getClassLoader().getResource("/").getPath();
	}
	public static String getCurrPath(Class<?> c){
		return c.getResource("").getPath();
	}
	public static String getProjectPath() throws IOException {
		File directory = new File("");// 参数为空
		return directory.getCanonicalPath();
	}

	public static void main(String[] args){

		try {

			String cpath = getPath("sql");
			File file = new File(cpath);
			for(File f:file.listFiles()){
				System.out.println(f.getPath());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
