package cn.lmz.mike.lmz.sys.node;

import java.util.ArrayList;
import java.util.List;

import cn.lmz.mike.common.log.O;
import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.expression.NodeExpression;
import cn.lmz.mike.lmz.sys.lexer.Block;
import cn.lmz.mike.lmz.sys.lexer.Row;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BlockNode extends ANode {
	
	private static Log log =LogFactory.getLog(BlockNode.class);
	
	private List<ANode> nlist = new ArrayList<ANode>();

	public BlockNode(Context ctx){
		super(ctx);
		//O.pn("["+this.getClass().getSimpleName()+"] ctx:"+ctx);
	}

	@Override
	public Object execute(Context ctx) throws Exception {
		// TODO Auto-generated method stub
		
		for(int i=0;i<nlist.size();i++){
			ANode node = nlist.get(i);
			
			try{
				if(ctx.getStack().size()==0){
					O.info("[R]"+node.getCodeStr());
				}
				//O.pn(ctx.getRunPath()+"R:"+node.getRowStr());
				//O.dn("B:"+node.toString(this.ctx));
				node.runNode(this.ctx);
				//Thread.sleep(100);
				Boolean isBreak = (Boolean)ctx.getCfg().get(Const.V_BREAK);
				if(isBreak!=null&&isBreak){
					break;
				}
				Object vreturn = ctx.getCfg().get(Const.V_RETURN);
				if(vreturn!=null){
					break;
				}
				
				
			}catch(Exception e){
				O.error("EXE:"+ctx.getRunCode()+node.getCodeStr());
				O.error("EXE:"+ctx.getRunCode()+node.toString(this.ctx));
				log.error(" Object execute(Context ctx) ",e);
				throw e;
			}
		}
		//O.pn("BE{"+this.getClass().getSimpleName()+"}"+this);
		return null;
	}
	
	public Object interpret(Block b) throws Exception{
		super.interpret(b);
		for(int i=0;i<b.getList().size();i++){
			Row r = (Row)b.getList().get(i);
			try{
				O.debug("EXP:"+r.getCodeStr());
				ANode an = NodeExpression.interpret(this.ctx, r);
				an.setCodeStr(r.getCodeStr());
				
				O.debug("NODE:"+an.toString(this.ctx));
				
				if(!(an instanceof FunctionNode)){
					nlist.add(an);
				}
			}catch(Exception e){
				O.error("EXP:"+ctx.getCodeStr()+">>"+e.getMessage());
				O.error("EXP:"+ctx.getExpCode());
				log.error(" Object interpret(Context ctx) ",e);
				throw e;
			}
		}
		
		return null;
	}
}
