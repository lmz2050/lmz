package cn.lmz.mike.common.exception;


import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.log.O;
public class LMZException extends Exception {
    
	private static final long serialVersionUID = -6987485102421839125L;

	public LMZException(Throwable e){
		super(e);
	}
	public LMZException(String p_name,String msg,Throwable e){
		super(p_name+"-"+msg,e);
	}
	public LMZException(String msg,Throwable e){
		super(msg,e);
	}
	public LMZException(String msg){
		super(msg,new Throwable());
	}
	
	public LMZException(Class<?> c,String msg,Throwable e){
		super((c.getName().replace(c.getPackage().getName()+".", "")+"-"+msg),e);
	}
	public LMZException(String simpleName, String m, Exception e,Object... objects) {
		super(getMsg(e,simpleName,m,objects),e);
	}
	
	private static String getMsg(Exception e,String simpleName, String m,Object... objects){
		if(e!=null){e.printStackTrace();}
		String msg = "";
		msg += simpleName+"."+m;
		msg += "\r\n<\r\n";
		for(Object o:objects){
			msg += MC.string.toStr(o)+"\r\n";
		}
		msg += ">\r\n";
		O.error(msg);
		return msg;
	}
	
}
