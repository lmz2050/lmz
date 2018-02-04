package cn.lmz.mike.common.log;


import cn.lmz.mike.common.MC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class O {

	private static Logger logger = LoggerFactory.getLogger(O.class);
	public static String lev="";
	private static final Logger log = null;
	public static boolean isDev=true;

	public static void dev(String str){
		if(isDev){
			getLog().info("[D]"+str);
		}
	}
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

	public static String getLev() {
		return lev;
	}
	public static void setLev(String lev) {
		setLev(lev, "Y");
	}
	public static void setLev(String lev,String showLog) {
		O.lev = lev;
		if("Y".equalsIgnoreCase(showLog)){
			O.info("log lev:"+lev);
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
	
	/**
     * 获取最原始被调用的堆栈信息
     * @return
     */
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
	
}
