package cn.lmz.mike.lmz.sys.exe;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.base.PkgU;
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
import com.xiaoleilu.hutool.log.level.Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class LExecute {

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
			O.setLev(Level.ERROR.toString(),"N");
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
		
		String rootPath = MC.string.getRoot();
		
		root.put(Const.ROOT, rootPath);
		root.getCfg().put(Const.ROOT, rootPath);
		root.getCfg().put(Const.$+"LMZ", this);
		root.getCfg().put(Const.$+"M",monitor);

		root.put(Const.$, new L(root));
		root.put(Const.$+"D", new MC.date());
		root.put(Const.$+"M",monitor);
		root.put(Const.$+"CTX",root);

		Map<String,String> pkgMap = PkgU.getClassNamePathMap("org.lmz.common","org.lmz.db","org.lmz.ext");
		root.getCfg().put(Const.PKG_MAP,pkgMap);

		List<String> pkgList = new ArrayList<String>();
		pkgList.add("java.lang");
		pkgList.add("java.util");
		root.getCfg().put(Const.PKG_LIST,pkgList);

		String initF = rootPath+INIT_LMZ;
		read(initF);
		
		inited = true;
	}

	public void read(String file) throws Exception{

		File f = new File(file);
		if(!f.exists()){
			throw new Exception("文件不存在："+file);
		}
		root.getCfg().put(Const.EXP_SRC_FILE, file);
		
		
		
		for(int i=0;i<explist.size();i++){
			IExpression exp = explist.get(i);
			exp.interpret(root);
		}
		
		if(O.isDev){
			List clist = (List)root.getCfg().get(Const.EXP_CODE_LIST);
			O.info("======================CODE========================");
			for(int i=0;i<clist.size();i++){
				O.info(clist.get(i).toString());
			}		
			Block b = (Block)root.getCfg().get(Const.EXP_BLOCK);
			O.info("======================BLOCK========================");
			O.info(b.toString());
		}
		
		BlockNode bn = (BlockNode)root.getCfg().get(Const.EXP_NODE);
		O.dev("RUN>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+file);
		bn.execute(root);
	}
	
	
	public void closeResource(){
		inited = false;
		explist = null;
		root = null;
	}
	
	
	public void execute(String file,Map params) throws Exception{
		if(!inited){
			init(params);
		}
		try{
			monitor.start();
			O.info(">>read<<---------------:"+file);		
			if(file!=null&&file.trim().length()>0){
				read(file);	
			}else{
				O.error("file is null :"+file);
				throw new FileNotFoundException("file is null :"+file);
			}
			
			monitor.end();
		}catch(Exception e){
			monitor.error();
			throw e;
		}finally{
			closeResource();
		}
		O.info(">>end<<");
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
