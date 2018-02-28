package cn.lmz.mike.lmz.sys.exe;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.V;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.lmz.sys.L;
import cn.lmz.mike.lmz.sys.context.Cfg;
import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.expression.IExpression;
import cn.lmz.mike.lmz.sys.expression.LexerExpression;
import cn.lmz.mike.lmz.sys.expression.NodeExpression;
import cn.lmz.mike.lmz.sys.expression.RowExpression;
import cn.lmz.mike.lmz.sys.lexer.Block;
import cn.lmz.mike.lmz.sys.node.BlockNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LExecute {

	private static final Logger log = LoggerFactory.getLogger(LExecute.class);

	public static final String INIT_LMZ="init/init.lmz";
	
	private boolean inited=false;
	private List<IExpression> explist = null;
	private Context root = null;
	private Monitor monitor = null;
		
	public void init(Map params) throws Exception{
		init(null,params);
	}
	public void init() throws Exception{
		init(null,null);
	}
	public void init(Context ctxi,Map params) throws Exception{
		if(!O.isDev){
			O.setLev(java.util.logging.Level.ALL.toString(),"N");
		}
		if(ctxi!=null){
			root = ctxi;
			inited = true;
			return;
		}
		
		Cfg cfg = new Cfg();
		if(params!=null&&params.size()>0){
			cfg.getCfg().putAll(params);
		}
		
		root = new Context();
		root.setCfg(cfg);
		Cfg.setCurrCtx(root);
		monitor = new Monitor(root);
		
		explist = new ArrayList<IExpression>();
		explist.add(new LexerExpression());
		explist.add(new RowExpression());
		explist.add(new NodeExpression());
		
		String rootPath = (String)cfg.get(V.APPHOME);
		//System.out.println(rootPath);
		
		root.put(Const.ROOT, rootPath);
		root.getCfg().put(Const.ROOT, rootPath);
		root.getCfg().put(Const.$+"LMZ", this);
		root.getCfg().put(Const.$+"M",monitor);

		root.put(Const.$, new L(root));
		root.put(Const.$+"D", new MC.date());
		root.put(Const.$+"M",monitor);
		root.put(Const.$+"CTX",root);

		Map<String,Class<?>> pkgMap = new HashMap<String,Class<?>>();
		root.getCfg().put(Const.PKG_MAP,pkgMap);

		String initF = rootPath+INIT_LMZ;
		log.info(">>init execute<<---------------:"+initF);
		read(initF);
		
		inited = true;
	}

	public void read(String file) throws Exception{

		File f = new File(file);
		if(!f.exists()){
			throw new Exception("file is not exists:"+file);
		}
		root.getCfg().put(Const.EXP_SRC_FILE, file);

		for(int i=0;i<explist.size();i++){
			IExpression exp = explist.get(i);
			exp.interpret(root);
		}
		
		if(O.isDev){
			List clist = (List)root.getCfg().get(Const.EXP_CODE_LIST);
			log.trace("======================CODE========================");
			for(int i=0;i<clist.size();i++){
				log.trace(clist.get(i).toString());
			}		
			Block b = (Block)root.getCfg().get(Const.EXP_BLOCK);
			log.trace("======================BLOCK========================");
			log.trace(b.toString());
		}
		
		BlockNode bn = (BlockNode)root.getCfg().get(Const.EXP_NODE);
		log.trace("RUN>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+file);
		bn.execute(root);
	}
	
	
	public void closeResource(){
		inited = false;
		explist = null;
		root = null;
	}
	
	
	public void execute(String file,Map params) throws Exception{

		File f = new File(file);
		if(!f.exists()){
			throw new Exception("file is not exists:"+file);
		}
		if(f.isDirectory()){
			throw new Exception("file is Directory:"+file);
		}

		if(!inited){
			init(params);
		}
		try{
			monitor.start();
			log.info(">>execute<<---------------:"+file);
			if(file!=null&&file.trim().length()>0){
				read(file);	
			}else{
				log.error("file is null :"+file);
				throw new FileNotFoundException("file is null :"+file);
			}
			
			monitor.end();
		}catch(Exception e){
			monitor.error();
			throw e;
		}finally{
			closeResource();
		}
		log.info(">>end<<");
	}
	
	public boolean isInited() {
		return inited;
	}
	public void setInited(boolean inited) {
		this.inited = inited;
	}
	public Context getRoot() {
		return root;
	}
	public void setRoot(Context root) {
		this.root = root;
	}
	
}
