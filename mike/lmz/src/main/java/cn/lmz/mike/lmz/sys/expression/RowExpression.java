package cn.lmz.mike.lmz.sys.expression;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.lmz.sys.context.Cfg;
import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.exception.ErrCodeException;
import cn.lmz.mike.lmz.sys.lexer.Block;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.Row;
import cn.lmz.mike.lmz.sys.util.TypeUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class RowExpression  implements IExpression {

	@Override
	public void interpret(Context ctx) throws Exception {

		List<Code> plist = (List<Code>)ctx.getCfg().get(Const.EXP_CODE_LIST);

		Block b = interpret(ctx,plist);
		ctx.getCfg().put(Const.EXP_BLOCK, b);

	}
	
	
	private Block interpret(Context ctx,List<Code> plist) throws Exception{
		Row r = new Row();
		Block b = new Block();
		int is_In_Block = 0; //是否在代码块之中
		boolean is_In_Digit=false;
		int is_In_For=0;
		int is_In_P = 0;//是否在小括号中
		String strToken="";
		
		List<Code> block_plist = new ArrayList<Code>();
		String rowStr="";
		
		for(int i=0;i<plist.size();i++){
			Code code = plist.get(i);
			rowStr+=code.getVal();
			
			if(TypeUtil.TYPE_OPER.equalsIgnoreCase(code.getType())&&" ".equalsIgnoreCase(code.getVal())){
				continue;
			}
			if("for".equalsIgnoreCase(code.getVal())){
				is_In_For=2;
			}
			/*
			if('$'==code.getCh()){
				Code nc = getNext(plist, i);
				if(nc!=null&&'{'==nc.getCh()){
					i++;
					nc = getNext(plist, i);
					String key="";
					while(nc!=null&&'}'!=nc.getCh()){
						key+=nc.getVal();
						i++;
						nc = getNext(plist, i);
					}
					i++;
					Code c = new Code(TypeUtil.TYPE_ID,key);
					
					
				}
			}
			*/
			
			if('{'==code.getCh()){
				is_In_Block++;
				
				if(is_In_Block==1){
					block_plist = new ArrayList<Code>();
					//r.add(code);
					continue;
				}
			}
			
			if('}'==code.getCh()){
				is_In_Block--;
				
				if(is_In_Block<0){
					
					String msg = getPreCode(50,plist, i);
					throw new Exception(" too many '}' in code: "+msg+code.getVal());
				}
				if(is_In_Block==0){
					Block bi = interpret(ctx,block_plist);
					String key = ctx.getCfg().put(bi);
					Code c = new Code(TypeUtil.TYPE_BLOCK,key,"{"+key+"}");
					
					r.add(c);
					Code nc = getNext(plist, i);
					if(is_In_P<=0&&(nc==null||!"else".equalsIgnoreCase(nc.getVal()))){
						//r.setRowStr(rowStr);
						b.add(r);
						r = new Row();
						rowStr = "";
					}
					continue;
				}
			}
			
			
			if(is_In_Block>0){
				
				block_plist.add(code);
				continue;
			}
			
			if('('==code.getCh()){
				is_In_P++;
			}
			
			if(')'==code.getCh()){
				is_In_P--;
			}
			
			if(TypeUtil.TYPE_KEY.equalsIgnoreCase(code.getType())&&"var".equalsIgnoreCase(code.getVal())){
				Code nc = getNext(plist, i);
				if(nc!=null){
					nc.setType(TypeUtil.TYPE_DEF);
				}
				continue;
			}
			
			if(TypeUtil.TYPE_DIGIT.equalsIgnoreCase(code.getType())){
				is_In_Digit = true;
				strToken+=code.getVal();
				continue;
			}else if(is_In_Digit){
				
				if('.'==code.getCh()){
					strToken+=code.getVal();
					continue;
				}else{
					
					String key = ctx.put(new BigDecimal(strToken));
					//System.out.println(key+"="+strToken);
					Code codedig = new Code(TypeUtil.TYPE_VAR,key,strToken);
					r.add(codedig);
					strToken="";
					is_In_Digit = false;
				}
				
				
			}
			
			
			if(';'==code.getCh()){
				//r.setRowStr(rowStr);
				if(is_In_For==0){
					if(is_In_P>0){
						throw new ErrCodeException(r, ErrCodeException.ERR_CODE_200);
					}else{
						if(!rowStr.startsWith("#!")){
							b.add(r);
						}
						rowStr = "";
						r = new Row();
						is_In_For=0;
					}
				}else{
					r.add(code);
					is_In_For--;
				}

				continue;
			}
			
			r.add(code);
		}
		if(r.getList().size()>0){
			
			//r.setRowStr(rowStr);
			rowStr="";
			b.add(r);
			r = new Row();
		}
		return b;
	}

	private String getPreCode(int n,List<Code> plist,int i){
		int k=i-n;
		if(k<0) k=0;
		String msg="";
		for(int j=k;j<i;j++){
			msg = msg+" "+plist.get(j).getVal();
		}
		return msg;
	}
	
	public Code getNext(List<Code> clist,int i){
		if(i<clist.size()-1){
			return clist.get(i+1);
		}
		return null;
	}
	
	public static void main(String[] args){
		try {
			String srcFile = MC.string.getRoot()+"input.txt";
			Context ctx = new Context();
			Cfg cfg = new Cfg();
			ctx.setCfg(cfg);
			ctx.getCfg().put(Const.EXP_SRC_FILE, srcFile);
			
			LexerExpression lexer = new LexerExpression();
			lexer.interpret(ctx);
			
			//QuoteExpression preexp = new QuoteExpression();
			//preexp.interpret(ctx);
			
			RowExpression rowexp = new RowExpression();
			rowexp.interpret(ctx);
			
			Block b = (Block)ctx.getCfg().get(Const.EXP_BLOCK);
			
			System.out.println(b.toString());
			//System.out.println(b.toString(ctx));
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
