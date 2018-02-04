package cn.lmz.mike.lmz.sys.lexer;

import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.node.ANode;
import cn.lmz.mike.lmz.sys.util.TypeUtil;

import java.util.List;


public class Code implements ICode,INode,Cloneable{

	private Character ch='x';
	private String type;
	private String val;
	private String codeStr;
	private Row r;

	public Code(){}

	public Code clone(){
		try {
			return (Code)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getV(Context ctx) throws Exception{
		Object v = null;
		if(val.startsWith("@j")){
			v = ctx.getCfg().get(val);
		}else{
			 v = ctx.get(val);
		}
		if(v==null) return v;
		if(v instanceof ANode){
			ANode an = (ANode) v;
			return an.runNode(ctx);
		}
		if(v instanceof Code){
			Code cn = (Code) v;
			return cn.getV(ctx);
		}
		
		if(v!=null&&v instanceof String){
			String vs = (String) v;
			if(vs.contains("${")||vs.contains("#{")){
				v = ctx.findValue(vs);
			}
		}
		
		return v;
	}

	public Code(String type,String val,Character ch){
		this.type = type;
		this.val = val;
		this.ch = ch;
		this.codeStr = val;
	}
	
	public Code(String type,String val){
		this.type = type;
		this.val = val;
		this.codeStr = val;
	}
	public Code(String type,String val,String codeStr){
		this.type = type;
		this.val = val;
		this.codeStr = codeStr;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}

	public String toString(){
		return "("+type+", "+val+")";
	}
	public String toString(Context ctx){
		if(TypeUtil.TYPE_BLOCK.equalsIgnoreCase(type)){
			Block b = (Block)ctx.getCfg().get(val);
			return b.toString(ctx);
		}else if(TypeUtil.TYPE_VAR.equalsIgnoreCase(type)){
			String str="["+type+", ";
			Object v = null;
			if(val.startsWith("@j")){
				v = ctx.getCfg().get(val);
			}else{
				 v = ctx.get(val);
			}
			if(v!=null&& v instanceof INode){
				INode n = (INode)v;
				str+=n.toString(ctx)+"]";
			}else{
				str+=v;
			}
			str+="]";
			return str;
		}
		return "("+type+", "+val+")";
	}

	public Row getR() {
		return r;
	}

	public void setR(Row r) {
		this.r = r;
	}

	public Code getNext(Row r){
		if(r==null){r = getR();}
		List<Code> rlist = r.getList();
		for(int i=0;i<rlist.size();i++){
			if(rlist.get(i)==this){
				if(i<rlist.size()-1){
					return rlist.get(i+1);
				}else{
					return null;
				}
			}
		}
		return null;
	}
	
	public Code getNext(){
		return getNext(null);
	}

	public Code getPrev(){
		return getPrev(null);
	}
	
	public Code getPrev(Row r){
		if(r==null){r = getR();}
		List<Code> rlist = getR().getList();
		for(int i=0;i<rlist.size();i++){
			if(rlist.get(i)==this){
				if(i>0){
					return rlist.get(i-1);
				}else{
					return null;
				}
			}
		}
		return null;
	}
	public String getCodeStr() {
		return codeStr;
	}

	public void setCodeStr(String codeStr) {
		this.codeStr = codeStr;
	}
	
	
}
