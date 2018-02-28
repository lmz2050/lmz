package cn.lmz.mike.lmz.sys.node;

import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.exception.RunCodeException;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.Row;
import cn.lmz.mike.lmz.sys.util.CastUtil;
import cn.lmz.mike.lmz.sys.util.TypeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OperNode extends ANode {

	private static final Logger log = LoggerFactory.getLogger(OperNode.class);

	private Code left;
	private Code right;
	private Code code;	
	
	public OperNode(Context ctx,Code left,Code code,Code right) throws Exception{
		super(ctx);
		this.left = left;
		this.right = right;
		this.code = code;
		Row r = new Row();
		r.add(left);
		r.add(code);
		r.add(right);
		super.interpret(r);
	}
	
	public OperNode(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object execute(Context ctx) throws Exception {
		Object v = null;
		if(code==null) return v;
		
		if("!".equalsIgnoreCase(code.getVal())){
			v = right.getV(ctx);
			Boolean bl = !CastUtil.getBoolean(v);
			log.debug(ctx.getRunCode()+code.getVal()+right.getVal()+"("+v+")==>"+bl);
			return bl;
		}else if("+A".equalsIgnoreCase(code.getVal())){
			v = right.getV(ctx);
			BigDecimal o = CastUtil.getBigDecimal(v);
			return o;
		}else if("-A".equalsIgnoreCase(code.getVal())){
			v = right.getV(ctx);
			BigDecimal o = CastUtil.getBigDecimal(v);
			return new BigDecimal("-"+o.toPlainString());
		}else if("++".equalsIgnoreCase(code.getVal())){
			Code c;
			if(left!=null){
				v = left.getV(ctx);
				c = left;
			}else{
				v = right.getV(ctx);
				c=right;
			}
			BigDecimal o = CastUtil.getBigDecimal(v);
			o = o.add(BigDecimal.ONE);
			ctx.put(c.getVal(),o,true);
			
			log.debug(ctx.getRunCode()+c.getVal()+"("+v+")"+code.getVal()+"==>"+o);
			
			return o;
		}else if("--".equalsIgnoreCase(code.getVal())){
			Code c;
			if(left!=null){
				v = left.getV(ctx);
				c = left;
			}else{
				v = right.getV(ctx);
				c=right;
			}
			BigDecimal o = CastUtil.getBigDecimal(v);
			o =  o.subtract(BigDecimal.ONE);
			ctx.put(c.getVal(),o,true);
			
			log.debug(ctx.getRunCode()+c.getVal()+"("+v+")"+code.getVal()+"==>"+o);
			
			return o;
		}else if("*".equalsIgnoreCase(code.getVal())){
			BigDecimal ol = CastUtil.getBigDecimal(left.getV(ctx));
			BigDecimal or = CastUtil.getBigDecimal(right.getV(ctx));
			return ol.multiply(or);
		}else if("/".equalsIgnoreCase(code.getVal())){
			BigDecimal ol = CastUtil.getBigDecimal(left.getV(ctx));
			BigDecimal or = CastUtil.getBigDecimal(right.getV(ctx));
			return ol.divide(or);
		}else if("%".equalsIgnoreCase(code.getVal())){
			BigDecimal ol = CastUtil.getBigDecimal(left.getV(ctx));
			BigDecimal or = CastUtil.getBigDecimal(right.getV(ctx));
			return ol.divideAndRemainder(or)[1];
		}else if("+".equalsIgnoreCase(code.getVal())){
			
			Object lo =left.getV(ctx);
			Object ro = right.getV(ctx);
			
			if(lo==null||!(lo instanceof BigDecimal)||ro==null||!(ro instanceof BigDecimal)){
				return lo+""+ro;
			}
			
			BigDecimal ol = CastUtil.getBigDecimal(left.getV(ctx));
			BigDecimal or = CastUtil.getBigDecimal(right.getV(ctx));
			return ol.add(or);
		}else if("-".equalsIgnoreCase(code.getVal())){
			BigDecimal ol = CastUtil.getBigDecimal(left.getV(ctx));
			BigDecimal or = CastUtil.getBigDecimal(right.getV(ctx));
			return ol.subtract(or);
		}else if("<".equalsIgnoreCase(code.getVal())){
			Comparable ol = CastUtil.getComparable(left.getV(ctx));
			Comparable or = CastUtil.getComparable(right.getV(ctx));
			Boolean bl = ol.compareTo(or)<0;
			log.debug(ctx.getRunCode()+left.getVal()+code.getVal()+right.getVal()+"("+ol+code.getVal()+or+")==>"+bl);
			return bl;
		}else if("<=".equalsIgnoreCase(code.getVal())){
			Comparable ol = CastUtil.getComparable(left.getV(ctx));
			Comparable or = CastUtil.getComparable(right.getV(ctx));
			return ol.compareTo(or)<=0;
		}else if(">".equalsIgnoreCase(code.getVal())){
			Comparable ol = CastUtil.getComparable(left.getV(ctx));
			Comparable or = CastUtil.getComparable(right.getV(ctx));
			return ol.compareTo(or)>0;
		}else if(">=".equalsIgnoreCase(code.getVal())){
			Comparable ol = CastUtil.getComparable(left.getV(ctx));
			Comparable or = CastUtil.getComparable(right.getV(ctx));
			return ol.compareTo(or)>=0;
		}else if("==".equalsIgnoreCase(code.getVal())){
			Comparable ol = CastUtil.getComparable(left.getV(ctx));
			Comparable or = CastUtil.getComparable(right.getV(ctx));
			Boolean bl = ol.compareTo(or)==0;
			
			log.debug(ctx.getRunCode()+left.getVal()+code.getVal()+right.getVal()+"("+ol+code.getVal()+or+")==>"+bl);
			return bl;
		}else if("!=".equalsIgnoreCase(code.getVal())){
			Comparable ol = CastUtil.getComparable(left.getV(ctx));
			Comparable or = CastUtil.getComparable(right.getV(ctx));
			return ol.compareTo(or)!=0;
		}else if("&&".equalsIgnoreCase(code.getVal())){
			Boolean ol = CastUtil.getBoolean(left.getV(ctx));
			Boolean or = CastUtil.getBoolean(right.getV(ctx));
			return ol&&or;
		}else if("||".equalsIgnoreCase(code.getVal())){
			Boolean ol = CastUtil.getBoolean(left.getV(ctx));
			Boolean or = CastUtil.getBoolean(right.getV(ctx));
			return ol||or;
		}else if("=".equalsIgnoreCase(code.getVal())){
			
			
			v = right.getV(ctx);
			
			/*
			if(v !=null&&(v instanceof String)){
				String vs = (String) v;
				v = ctx.findValue(vs);
			}
			*/

			if(TypeUtil.TYPE_DEF.equalsIgnoreCase(left.getType())){
				ctx.put(left.getVal(),v,false);
			}else{
				ctx.put(left.getVal(),v,true);
			}
				

			log.debug(ctx.getRunCode()+left.getVal()+code.getVal()+right.getVal()+"("+left.getVal()+code.getVal()+v+")");
			
			return null;
		}else if(">>".equalsIgnoreCase(code.getVal())){
			
			
			v = left.getV(ctx);
			
			if(v !=null&&(v instanceof List)){
				List<String> vs = (List<String>) v;
				List<String> vsn = new ArrayList<String>();
				for(int i=0;i<vs.size();i++){
					String vi = ctx.findValue(vs.get(i));
					vsn.add(vi);
				}
				v = vsn;
			}
			
			
			ctx.put(right.getVal(),v,true);

			log.debug(ctx.getRunCode()+left.getVal()+code.getVal()+right.getVal()+"("+left.getVal()+code.getVal()+v+")");
			
			return null;
		}else{
			throw new RunCodeException(this," missing >> "+ code.getVal()+getCode());
		}
		
	}
	
	public String toString(Context ctx){
		return "["+this.getClass().getSimpleName()+"]"+getString(left,ctx)+getString(code,ctx)+getString(right,ctx);
	}
}
