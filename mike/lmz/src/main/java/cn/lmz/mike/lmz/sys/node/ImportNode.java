package cn.lmz.mike.lmz.sys.node;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.io.ScanU;
import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.exe.LExecute;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.ICode;
import cn.lmz.mike.lmz.sys.util.SysU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ImportNode extends ANode {

	private static final Logger log = LoggerFactory.getLogger(ImportNode.class);

	private List<String> sourceList = new ArrayList<String>();

	public ImportNode(Context ctx) {
		super(ctx);
	}

	@Override
	protected Object execute(Context ctx) throws Exception {

		if(sourceList!=null&&sourceList.size()>0){
			for(int i=0;i<sourceList.size();i++){
				
				String sname = sourceList.get(i).split(":")[1];
				if(sname.startsWith("@")){
					sname = ctx.findValue((String)ctx.get(sname));
				}
				log.debug("load source:"+sname);
				if(sname.endsWith(".lmz")){
					
					String nowfile = (String)ctx.getCfg().get(Const.EXP_SRC_FILE);
					
					LExecute exp = (LExecute)ctx.getCfg().get(Const.$+"LMZ");
					exp.read(sname);
					
					ctx.getCfg().put(Const.EXP_SRC_FILE,nowfile);
					
				}else if(sname.endsWith(".properties")){
					ctx.getCtx().putAll(MC.prop.getMap(sname));
				}

			}
		}
		
		
		return null;
	}
	
	
	public Object interpret(ICode c)  throws Exception{
		super.interpret(c.getR());
		Map<String,Class<?>> pkgMap = (Map<String,Class<?>>)ctx.getCfg().get(Const.PKG_MAP);

		Code nc = c.getNext();
		String name="";
		while(nc!=null){
			if(",".equalsIgnoreCase(nc.getVal())&&name.trim().length()>0){
				if(name.contains(":")){
					if(!sourceList.contains(name)){
						sourceList.add(name);
						log.debug("import source:"+name);
					}
				}else{
					pkgMap.putAll(ScanU.getClassNamePathMap(name));
					log.debug("import pakage:{},size:{}",name,pkgMap.size());
				}

				name = "";
			}else{
				name+=nc.getVal();
			}
			nc = nc.getNext();
		}
		
		if(name.trim().length()>0){
			
			if(name.contains("F:")){
				if(!sourceList.contains(name)){
					sourceList.add(name);
					log.debug("import source:"+name);
				}
			}else{
				pkgMap.putAll(ScanU.getClassNamePathMap(name));
				log.debug("import pakage:{},size:{}",name,pkgMap.size());
			}
			name = "";
		}
		
		return null;
	}

}
