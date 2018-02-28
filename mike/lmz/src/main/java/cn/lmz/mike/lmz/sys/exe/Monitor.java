package cn.lmz.mike.lmz.sys.exe;

import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.node.FunctionNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Monitor {

	private static final Logger log = LoggerFactory.getLogger(Monitor.class);
	
	public static final String $M_FUN_START="$M_FUN_START";
	public static final String $M_FUN_END="$M_FUN_END";
	public static final String $M_FUN_ERROR="$M_FUN_ERROR";
	
	private Context ctx=null;
	
	public Monitor(Context ctx){
		this.ctx = ctx;
	}

	public FunctionNode getStart() {
		return (FunctionNode)ctx.get($M_FUN_START);
	}
	public void setStart(FunctionNode start) {
		ctx.put($M_FUN_START,start);
	}
	public FunctionNode getEnd() {
		return (FunctionNode)ctx.get($M_FUN_END);
	}
	public void setEnd(FunctionNode end) {
		ctx.put($M_FUN_END,end);
	}
	public FunctionNode getError() {
		return (FunctionNode)ctx.get($M_FUN_ERROR);
	}
	public void setError(FunctionNode error) {
		ctx.put($M_FUN_ERROR,error);
	}
	
	
	public void start() throws Exception{
		log.trace("monitor started："+ ctx.getRunFile());
		if(getStart()!=null){
			getStart().setParams(ctx).runNode(ctx);
		}
	}
	public void end() throws Exception{
		log.trace("monitor ended："+ctx.getRunFile());
		if(getEnd()!=null){
			getEnd().setParams(ctx).runNode(ctx);
		}
	}
	public void error() throws Exception{
		log.trace("monitor errored："+ctx.getRunFile());
		if(getError()!=null){
			getError().setParams(ctx).runNode(ctx);
		}
	}
	
}
