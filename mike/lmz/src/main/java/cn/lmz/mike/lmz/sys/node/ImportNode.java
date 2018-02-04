package cn.lmz.mike.lmz.sys.node;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.lmz.sys.context.Const;
import cn.lmz.mike.lmz.sys.context.Context;
import cn.lmz.mike.lmz.sys.exe.LExecute;
import cn.lmz.mike.lmz.sys.lexer.Code;
import cn.lmz.mike.lmz.sys.lexer.ICode;

import java.util.*;


public class ImportNode extends ANode {
	
	private List<String> surList = new ArrayList<String>();

	public ImportNode(Context ctx) {
		super(ctx);
	}

	@Override
	protected Object execute(Context ctx) throws Exception {

		if(surList!=null&&surList.size()>0){
			for(int i=0;i<surList.size();i++){
				
				String sname = surList.get(i).split(":")[1];
				if(sname.startsWith("@")){
					sname = ctx.findValue((String)ctx.get(sname));
				}
				O.debug("load source:"+sname);
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
		List<String> pkgList = (List<String>)ctx.getCfg().get(Const.PKG_LIST);
		Map<String,String> pkgMap = (Map<String,String>)ctx.getCfg().get(Const.PKG_MAP);

		Code nc = c.getNext();
		String name="";
		while(nc!=null){
			if(",".equalsIgnoreCase(nc.getVal())&&name.trim().length()>0){
				if(name.contains(":")){
					if(!surList.contains(name)){
						surList.add(name);
						O.debug("import source:"+name);
					}
				}else{
					if(name.startsWith("java.")){
						if(!pkgList.contains(name)){
							pkgList.add(name);
						}
					}else {
						pkgMap.putAll(ScanU.getClassNamePathMap(name));
					}
					O.debug("import pakage:"+name);
				}

				name = "";
			}else{
				name+=nc.getVal();
			}
			nc = nc.getNext();
		}
		
		if(name.trim().length()>0){
			
			if(name.contains("F:")){
				if(!surList.contains(name)){
					surList.add(name);
					O.debug("import source:"+name);
				}
			}else{
				if(name.startsWith("java.")){
					if(!pkgList.contains(name)){
						pkgList.add(name);
					}
				}else {
					pkgMap.putAll(ScanU.getClassNamePathMap(name));
				}
				O.debug("import pakage:"+name);
			}
			name = "";
		}
		
		return null;
	}

}
