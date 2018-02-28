package cn.lmz.mike.lmz.sys.context;

import cn.lmz.mike.lmz.sys.lexer.INode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Context {

	private Cfg cfg = null;
	private Context parentCtx = null;
	
	private Stack<INode> stack = new Stack<INode>();
	private Map<String,Object> ctx = new HashMap<String,Object>();
	
	public static final Pattern p = Pattern.compile("\\$\\{(\\w+)\\}");

	public String getRunFile(){
		return cfg==null?"":(String)cfg.get(Const.EXP_SRC_FILE);
	}
	public void push(INode n){
		stack.push(n);
	}
	public INode pop(){
		return stack.pop();
	}
	public Stack<INode> getStack(){
		return this.stack;
	}
	public String getRunCode(){
		String msg="[";
		for(int i=0;i<stack.size();i++){
			if(i==stack.size()-1){
				msg+=stack.get(i).getClass().getSimpleName();
			}else{
				msg+=stack.get(i).getClass().getSimpleName()+">";
			}
		}
		msg+="]";
		return msg;
	}

	public String getCodeStr(){
		String msg="[\r\n";
		for(int i=0;i<stack.size();i++){
			msg+=stack.get(i).getCodeStr()+"\r\n";
		}
		msg+="]\r\n";
		return msg;	
	}
	
	public String getExpCode(){
		String msg="[\r\n";
		for(int i=0;i<stack.size();i++){
			msg+=stack.get(i).toString()+"\r\n";
		}
		msg+="]\r\n";
		return msg;			
	}
	
	public void put(String key,Object value,boolean isAll){
		ctx.put(key, value);
		if(isAll){
			Context parentCtxi = parentCtx;
			while(parentCtxi!=null){
				if(parentCtxi.getCtx().containsKey(key)){
					parentCtxi.put(key, value);
				}
				parentCtxi = parentCtxi.getParentCtx();
			}
		}
	}
	
	public void putHave(Context ctxi){
		for(Map.Entry<String, Object> en:ctx.entrySet()){	
			String key = en.getKey();
			if(ctxi.getCtx().containsKey(key)){
				ctx.put(key, ctxi.get(key));
			}		
		}
	}

	public void setParentCtx(Context ctx){
		this.parentCtx = ctx;
	}
	public Context getParentCtx(){
		return this.parentCtx;
	}
	
	@SuppressWarnings("unchecked")
	public Context init(){
		this.ctx.clear();
		putAll(parentCtx);
		if(parentCtx!=null){
			stack = (Stack<INode>)parentCtx.getStack().clone();
		}

		return this;
	}
	
	public Context getChildCtx(){
		Context ctx_c = new Context();
		ctx_c.putAll(this);
		ctx_c.setCfg(cfg);
		ctx_c.setParentCtx(this);
		return ctx_c;
	}
	
	
	
	
	
	
	public void put(String key,Object value){
		ctx.put(key, value);
	}

	public String put(Object value){
		
		String key = cfg.getIKey();
		//O.debug("--"+key+":"+value);
		ctx.put(key, value);
		return key;
	}
	
	public Object get(String key){
		return ctx.get(key);
	}
	
	public String findValue(String str){
		if(str==null) return null;

		if(str.contains("${")){
			StringBuffer sb = new StringBuffer();
			Matcher m = p.matcher(str);
			while(m.find()){
				String strK = m.group(1);
				if(ctx.containsKey(strK)){
					strK=get(strK)+"";
				}
				m.appendReplacement(sb, strK);
			}
			m.appendTail(sb);
			return sb.toString();
		}

		if(str.contains("#{")){
			StringBuffer sb = new StringBuffer();
			Matcher m = p.matcher(str);
			while(m.find()){
				String strK = m.group(1);
				if(cfg.getCfg().containsKey(strK)){
					strK=cfg.getCfg().get(strK)+"";
				}
				m.appendReplacement(sb, strK);
			}
			m.appendTail(sb);
			return sb.toString();
		}
		return str;
	}

	public void putAll(Context ctxi){
		if(ctxi==null) return;
		ctx.putAll(ctxi.getCtx());
	}

	public Map<String, Object> getCtx() {
		return ctx;
	}

	public void setCtx(Map<String, Object> ctx) {
		this.ctx = ctx;
	}
	public Cfg getCfg() {
		return cfg;
	}
	public void setCfg(Cfg cfg) {
		this.cfg = cfg;
	}

	
}
