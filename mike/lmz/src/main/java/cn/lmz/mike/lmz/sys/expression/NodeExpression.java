package cn.lmz.mike.lmz.sys.expression;

import cn.lmz.mike.common.log.O;
import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.exception.ErrCodeException;
import cn.lmz.mike.lmz.sys.lexer.*;
import cn.lmz.mike.lmz.sys.node.*;
import cn.lmz.mike.lmz.sys.util.TypeUtil;

public class NodeExpression implements IExpression {

	
	@Override
	public void interpret(Context ctx) throws Exception {

		Block b = (Block)ctx.getCfg().get(Const.EXP_BLOCK);

		BlockNode bn = NodeExpression.interpret(ctx,b);

		ctx.getCfg().put(Const.EXP_NODE, bn);
	}
	
	public static BlockNode interpret(Context ctx,Block b) throws Exception{
		BlockNode bn = new BlockNode(ctx);
		bn.interpret(b);
		return bn;
	}
	
	public static ANode interpret(Context ctx, Row cl) throws Exception {
		ANode n = null;
		ctx.push(cl);
		if(cl.getList().size()==0){
			return null;
		}
		O.dev("[EXP]"+ctx.getCodeStr()+cl.getCodeStr());
		
		TypeUtil tu = new TypeUtil();
		boolean contain_oper = false;
		for(int i=0;i<cl.getList().size();i++){
			Code c = (Code)cl.getList().get(i);

			if("while".equalsIgnoreCase(c.getVal())){
				
				WhileNode wn = new WhileNode(ctx);
				wn.interpret(c);
				n = wn;	
				
				break;
				
			}else if("if".equalsIgnoreCase(c.getVal())){
				
				IfNode iff = new IfNode(ctx);
				iff.interpret(c);
				n = iff;
				break;
				
			}else if('('==c.getCh()){
				
				MethodNode mn = new MethodNode(ctx);
				Row r = (Row)mn.interpret(c);
				
				n = NodeExpression.interpret(ctx, r);
				
				break;

			}else if("new".equalsIgnoreCase(c.getVal())){
				
				NewNode nn = new NewNode(ctx);
				Row r = (Row)nn.interpret(c);
				
				n = NodeExpression.interpret(ctx, r);
				
				break;
				
			}else if("break".equalsIgnoreCase(c.getVal())){
				
				BreakNode bn = new BreakNode(ctx);
				bn.interpret(c);
				
				n = bn;
				
				break;
			}else if("return".equalsIgnoreCase(c.getVal())){
				
				ReturnNode bn = new ReturnNode(ctx);
				bn.interpret(c);
				
				n = bn;
				
				break;
			}else if("@".equalsIgnoreCase(c.getVal())){
				
				ImportNode bn = new ImportNode(ctx);
				bn.interpret(c);
				
				n = bn;
				
				break;
			}else if("for".equalsIgnoreCase(c.getVal())){
				
				ForNode bn = new ForNode(ctx);
				bn.interpret(c);
				
				n = bn;
				
				break;
			}else if("function".equalsIgnoreCase(c.getVal())){
				
				FunctionNode fn = new FunctionNode(ctx);
				fn.interpret(c);
				
				n = fn;
				
				break;
			}else if(tu.isOperator(c.getCh())){
				
				Row r = interpretOper(c);
				
				n = NodeExpression.interpret(ctx,r);
				
				break;
			}else if(TypeUtil.TYPE_OPER.equalsIgnoreCase(c.getType())){
				contain_oper = true;
			}
		}
		
		if(n==null){
			if(contain_oper){
				cl = getNoOperNode(ctx,cl);
			}
			
			if(cl.getList().size()>1){
				O.error(cl.getList().get(0).getCodeStr()+"--"+cl.getList().get(1).getCodeStr());
				O.error(cl.getCodeStr()+"="+cl.toString());
				throw new ErrCodeException(cl.getList().get(1),"code lenth["+cl.getList().size()+"]");
			}
			
			Code c = (Code)cl.getList().get(0);
			Object v = ctx.getCfg().get(c.getVal());
			if(v instanceof ANode){
				n = (ANode) v;
			}else{
				n = new ValueNode(ctx);
				n.interpret(c);
			}
			
		}

		ctx.pop();
		return n;
	}

	public static Row interpretOper(Code c)  throws Exception{
		
		Code newcode = null;
		Row leftr = null;
		Row rightr = null;
		TypeUtil tu = new TypeUtil();

		Code ncode = c.getNext();
		
		if('='==c.getCh()&&'='==ncode.getCh()){								
			newcode = new Code(TypeUtil.TYPE_OPER,"==");				
			leftr = getPrevCodes(c);
			rightr = getNextCodes(ncode);						
		}else if('&'==c.getCh()&&'&'==ncode.getCh()){		
			newcode = new Code(TypeUtil.TYPE_OPER,"&&");			
			leftr = getPrevCodes(c);
			rightr = getNextCodes(ncode);
		}else if('!'==c.getCh()&&'='==ncode.getCh()){	
			newcode = new Code(TypeUtil.TYPE_OPER,"!=");			
			leftr = getPrevCodes(c);
			rightr = getNextCodes(ncode);
		}else if('>'==c.getCh()&&'='==ncode.getCh()){		
			newcode = new Code(TypeUtil.TYPE_OPER,">=");			
			leftr = getPrevCodes(c);
			rightr = getNextCodes(ncode);
		}else if('<'==c.getCh()&&'='==ncode.getCh()){		
			newcode = new Code(TypeUtil.TYPE_OPER,"<=");			
			leftr = getPrevCodes(c);
			rightr = getNextCodes(ncode);
		}else if('|'==c.getCh()&&'|'==ncode.getCh()){		
			newcode = new Code(TypeUtil.TYPE_OPER,"||");			
			leftr = getPrevCodes(c);
			rightr = getNextCodes(ncode);
		}else if('+'==c.getCh()&&'+'==ncode.getCh()){		
			newcode = new Code(TypeUtil.TYPE_OPER,"++");			
			leftr = getPrevCodes(c);
			rightr = getNextCodes(ncode);
		}else if('-'==c.getCh()&&'-'==ncode.getCh()){		
			newcode = new Code(TypeUtil.TYPE_OPER,"--");			
			leftr = getPrevCodes(c);
			rightr = getNextCodes(ncode);
		}else if('+'==c.getCh()&&c.getPrev()==null){		
			newcode = new Code(TypeUtil.TYPE_OPER,"+A");			
			leftr = getPrevCodes(c);
			rightr = getNextCodes(ncode);
		}else if('-'==c.getCh()&&c.getPrev()==null){		
			newcode = new Code(TypeUtil.TYPE_OPER,"-A");			
			leftr = getPrevCodes(c);
			rightr = getNextCodes(ncode);
		}else if(tu.isOperator(c.getCh())){
			newcode = new Code(TypeUtil.TYPE_OPER,c.getVal());			
			leftr = getPrevCodes(c);
			rightr = getNextCodes(c);
		}

		
		Row r = new Row();
		r.addAll(leftr);
		r.add(newcode);
		r.addAll(rightr);
		
		return r;
		
	};

	
	
	private static boolean isInOper(String oper,String[] ops){
		//O.pn(oper+"--"+ops.toString());
		for(int k=0;k<ops.length;k++){
			String op = ops[k];
			if(op.equalsIgnoreCase(oper)){
				return true;
			}
		}
		return false;
	}

	private static Row lev(Context ctx, ICodeList cl, String[] ops) throws Exception{
		Row r = new Row();
		for(int i=0;i<cl.getList().size();i++){			
			Code c = (Code)cl.getList().get(i);
			
			if(isInOper(c.getVal(),ops)){
				Code left = c.getPrev();
				Code right = c.getNext();
				i++;

				OperNode on = new OperNode(ctx,left,c,right);
				on.interpret(c);
				Code codenew = new Code(TypeUtil.TYPE_VAR,ctx.getCfg().put(on));
				
				if(left!=null){
					r.remove(left);
				}
				
				r.add(codenew);
				r.addAll(getNextCodes(right));

				return r;
			}
			
			r.add(c);
		}
		
		return null;
	}
	
	private static Row getLev(Context ctx,Row r,String[] ops) throws Exception{
		Row pre = r;
		while(r!=null){
			pre = r;
			r = lev(ctx,r,ops);
		}
		return pre;
	}
	
	public static Row getNoOperNode(Context ctx,ICodeList cl)  throws Exception{
		Row r = (Row) cl;
		
		r = getLev(ctx,r, TypeUtil.op_lev0);
		r = getLev(ctx,r, TypeUtil.op_lev1);
		r = getLev(ctx,r, TypeUtil.op_lev2);
		r = getLev(ctx,r, TypeUtil.op_lev3);
		r = getLev(ctx,r, TypeUtil.op_lev4);
		r = getLev(ctx,r, TypeUtil.op_lev5);
		r = getLev(ctx,r, TypeUtil.op_lev6);
		r = getLev(ctx,r, TypeUtil.op_lev7);
		
		for(int i=0;i<r.getList().size();i++){
			Code c = r.getList().get(i);
			if(TypeUtil.TYPE_OPER.equalsIgnoreCase(c.getType())){
				throw new ErrCodeException(c," error oper code ");
			}
		}
		
		return r;
	};
	

	
	public static Row getNextCodes(ICode c){
		if(c==null) return null;
		Row r = new Row();
		Code nc = c.getNext();
		while(nc!=null){
			r.add(nc);
			nc = nc.getNext(c.getR());
		}
		return r;
	}
	public static Row getPrevCodes(ICode c){
		if(c==null) return null;
		Row r = new Row();
		Code nc = c.getPrev();
		while(nc!=null){
			r.add(nc);
			nc = nc.getPrev(c.getR());
		}
		r.reverse();
		return r;
	}	
}
