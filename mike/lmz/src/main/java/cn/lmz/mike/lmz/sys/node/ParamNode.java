package cn.lmz.mike.lmz.sys.node;

import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.expression.NodeExpression;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.ICodeList;
import cn.lmz.mike.lmz.sys.lexer.Row;

import java.util.ArrayList;
import java.util.List;


public class ParamNode extends ANode {

	private List<ANode> rnlist = new ArrayList<ANode>();
	private Row paramr  = null;
	
	public ParamNode(Context ctx) {
		super(ctx);
	}
	
	
	public Object interpret(ICodeList cl) throws Exception {
		super.interpret(cl);
		
		paramr = (Row)cl;

		Row r = new Row();
		String rstr="";
		
		int in_cdt = 0;
		
		for(int i=0;i<paramr.getList().size();i++){
			Code code = paramr.getList().get(i);
			
			if('('==code.getCh()){
				in_cdt++;
			}
			if(')'==code.getCh()){
				in_cdt--;
			}
			
			if(in_cdt<=0&&','==code.getCh()){
				ANode rnode = NodeExpression.interpret(ctx, r);
				rnlist.add(rnode);
				
				this.codeStr = rstr;
				rstr="";
				
				r = new Row();
			}else{
				r.add(code);
				rstr+=getCodeStr(code);
			}
			
		}
		
		if(r.getList().size()>0){
			ANode rnode = NodeExpression.interpret(ctx, r);
			rnlist.add(rnode);
		}
		
		return null;
	}
	

	@Override
	protected Object execute(Context ctx) throws Exception {
		// TODO Auto-generated method stub
		if(rnlist.size()==0) return null;
		Object[] params = new Object[rnlist.size()];
		
		for(int i=0;i<rnlist.size();i++){
			ANode rni = rnlist.get(i);
			if(rni instanceof FunctionNode){
				params[i] = rni;
			}else{
				params[i] = rni.runNode(ctx);
			}
		}
		
		
		
		return params;
	}

	
	

	public Row getParamr() {
		return paramr;
	}


	public void setParamr(Row paramr) {
		this.paramr = paramr;
	}

	public String toString(Context ctx){
		String str = "";
		for(int i=0;i<rnlist.size();i++){
			ANode n = rnlist.get(i);
			if(n==null){ 
				str+="";
			}else{
				str+=n.toString(ctx);
			}
			str+=",";
		}
		
		return "["+this.getClass().getSimpleName()+"]"+str;
	}
	
}
