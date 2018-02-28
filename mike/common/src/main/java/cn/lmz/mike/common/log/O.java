package cn.lmz.mike.common.log;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;


public class O {

	private static Logger log = null;
	public static String lev="";
	public static boolean isDev=false;

	public static String getLev() {
		return lev;
	}
	public static void setLev(String lev) {
		setLev(lev, "Y");
	}
	public static void setLev(String lev,String showLog) {
		O.lev = lev;
		changeLogLev(lev);
		if("Y".equalsIgnoreCase(showLog)){
			log.info("log lev:"+lev);
		}
	}

	public static void pNull(Object o,String message) throws Exception{
		if(o==null){
			throw new Exception(message);
		}
	}

	/*
	public static void pn(Object str){
		info(str);
	}
	public static void warn(Object str){
		getLog().warn(MC.string.toStr(str));
	}
	public static void debug(Object str){
		getLog().debug(MC.string.toStr(str));
	}		
	public static void info(Object str){
		getLog().info(MC.string.toStr(str));
	}
	public static void trace(Object str){
		getLog().trace(MC.string.toStr(str));
	}
	public static void error(Object str){
		getLog().error(MC.string.toStr(str));
	}
	public static void error(Object str,Throwable e){
		getLog().error(MC.string.toStr(str),e);
	}	
	public static void pNull(Object o,String message) throws Exception{
		if(o==null){
			throw new Exception(message);
		}
	}

	public static Logger getLog()
	{
        StackTraceElement caller = findCaller();//最原始被调用的堆栈对象
        Logger log = null;
        if(caller == null){
        	log = LoggerFactory.getLogger(O.class);
        }else{
        	log =LoggerFactory.getLogger(caller.getClassName() + "." + caller.getMethodName() + "() Line: " + caller.getLineNumber());
        }
        //log.setLevel(Level.toLevel(lev));
        return log;
	}

    private static StackTraceElement findCaller() {
        // 获取堆栈信息
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        if(null == callStack){
        	return null;
        }
        // 最原始被调用的堆栈信息
        StackTraceElement caller = null;
        // 日志类名称
        String logClassName = O.class.getName();
        // 循环遍历到日志类标识
        boolean isEachLogClass = false;
 
        // 遍历堆栈信息，获取出最原始被调用的方法信息
        for (StackTraceElement strackTraceEle : callStack) {
            // 遍历到日志类
            if(logClassName.equals(strackTraceEle.getClassName())) {
                isEachLogClass = true;
            }
            // 下一个非日志类的堆栈，就是最原始被调用的方法
            if(isEachLogClass) {
                if(!logClassName.equals(strackTraceEle.getClassName())) {
                    isEachLogClass = false;
                    caller = strackTraceEle;
                    break;
                }
            }
        }
        return caller;
    }
    */

	//log4j.configurationFile
	public static void initLog(String cfgName){
		System.out.println(cfgName);
		InputStream fis = null;
		try {
			ConfigurationSource source;
			//String relativePath = "config" + System.getProperty("file.separator") + "log4j2.xml";
			File f = new File(cfgName);
			if(!f.exists()){
				f = new File(O.class.getClassLoader().getResource(cfgName).getPath());
			}
			if(f.exists()){
				fis = new FileInputStream(f);
				Configurator.initialize(null,new ConfigurationSource(fis,f));
				log = LoggerFactory.getLogger(O.class);
			}else{
				System.out.println("cfg file not find:"+cfgName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			fis = null;
		}
	}

	public static void changeLogLev(String lev){
		Collection<org.apache.logging.log4j.core.Logger> notCurrentLoggerCollection = org.apache.logging.log4j.core.LoggerContext.getContext(false).getLoggers();
		Collection<org.apache.logging.log4j.core.Logger> currentLoggerCollection = org.apache.logging.log4j.core.LoggerContext.getContext().getLoggers();
		Collection<org.apache.logging.log4j.core.Logger> loggerCollection = notCurrentLoggerCollection;
		loggerCollection.addAll(currentLoggerCollection);
		for (org.apache.logging.log4j.core.Logger logger:loggerCollection){
			logger.setLevel(org.apache.logging.log4j.Level.toLevel(lev));
		}
	}
	
}
