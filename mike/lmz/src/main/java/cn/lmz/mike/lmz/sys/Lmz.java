package cn.lmz.mike.lmz.sys;

import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.V;
import cn.lmz.mike.common.log.O;
import cn.lmz.mike.lmz.sys.exe.LExecute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

public class Lmz {

	private static Logger log = null;

	public void execute(String[] args) throws Exception{
		//初始化log
		String APPHOME = L.string.getRoot();
		String log4jCfg = System.getProperty(V.LOG_CFG);
		if(L.string.isBlank(log4jCfg)){
			log4jCfg = APPHOME+"etc/log4j2.xml";
			O.initLog(log4jCfg);
			System.setProperty(V.LOG_CFG,log4jCfg);
		}
		log = LoggerFactory.getLogger(Lmz.class);

		log.info("START-"+args.length);

		Map<String,Object> val = new LinkedHashMap<String,Object>();
		val.put(V.APPHOME,APPHOME);

		if(args!=null&&args.length>0){
			String param = args[0];
			log.debug(param);
			if(param!=null&&param.trim().length()>0){
				String[] vs = param.split(",");
				for(int i=0;i<vs.length;i++){
					String vss = vs[i];
					int ii=vss.indexOf(":");
					String key=vss.substring(0,ii).trim();
					String vv=vss.substring(ii+1).trim();
					val.put(key, vv);
				}
			}
		}

		String lp=(String)val.get("@LP@");//run cfg -d
		String file = (String)val.get("@LF@");
		String rpwd = (String)val.get("@LD@");//run path
		if(rpwd==null) rpwd = "";
		if(lp==null) lp="";

		boolean isBatch=false;
		if(lp.trim().startsWith("-")){
			
			if(lp.contains("d")||lp.contains("D")){
				O.changeLogLev("trace");
			}
			
			if(lp.contains("f")||lp.contains("F")){
				log.trace("batch mode");
				isBatch = true;
				val.put("$BATCH", true);
			}
			
		}else{
			if(!L.string.isBlank(lp)) {
				file = lp;
			}
		}
		
		if(file!=null&&file.trim().length()>0){

			if(isBatch){
				if(!file.startsWith("/")&&!file.contains(":")){				
					if(file.contains("/")){
						file = rpwd+"/"+file;
					}else{
						file = MC.string.getRoot()+"cfg/"+file;
					}
				}
				
				log.info("batch folder:{}",file);
				File lfile = new File(file);
				if(!lfile.exists()){
					throw new Exception("file not exists!"+file);
				}
				if(!lfile.isDirectory()){
					throw new Exception("file is not Directory!"+file);
				}
				List<Lmzrpt> rptList = getRptList(file);
				
				for(int i=0;i<rptList.size();i++){
					Lmzrpt rpt = rptList.get(i);
					
					LExecute exe = new LExecute();
					exe.execute(rpt.getName(), val);
					
				}

			}else{
				if(!file.startsWith("/")&&!file.contains(":")){
					file = rpwd+"/"+file;
				}
				new LExecute().execute(file, val);
			}
		}else{
			log.error("error params:{}",args==null?null:args[0]);
		}

		log.info("END-");
	}
	
	
	public List<Lmzrpt> getRptList(String file){
		File lfile = new File(file);
		List<Lmzrpt> rptList = new LinkedList<Lmzrpt>();
		if(lfile.exists()&&lfile.isDirectory()){
			File[] lmzfs = lfile.listFiles();
			if(lmzfs!=null&&lmzfs.length>0){
				String fname=".lmz";
				for(int j=0;j<lmzfs.length;j++){
					File lmzf = lmzfs[j];
					if(lmzf.getName().endsWith(fname)){
						Lmzrpt rptj = getLmzrpt(lmzf);
						rptList.add(rptj);
					}
				}
				ComparatorRpt c = new ComparatorRpt();
				Collections.sort(rptList, c);
			}
		}
		return rptList;
	}
	
	public Lmzrpt getLmzrpt(File file){
		Lmzrpt rpt = new Lmzrpt();
		Integer ii=1;
		try{
			ii = new Integer(file.getName().split("_")[0]);
		}catch(Exception e){}
		rpt.setName(file.getAbsolutePath());
		rpt.setI(ii);
		return rpt;
	}
	class Lmzrpt{
		private Integer i;
		private String name;

		public Integer getI() {
			return i;
		}
		public void setI(Integer i) {
			this.i = i;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	class ComparatorRpt implements Comparator{

		 public int compare(Object arg0, Object arg1) {
			 Lmzrpt rpt0=(Lmzrpt)arg0;
			 Lmzrpt rpt1=(Lmzrpt)arg1;
			 return rpt0.getI().compareTo(rpt1.getI());
		 } 
	}
	
	public static void main(String[] args) throws Exception {
		//args = new String[2];
		//args[0]="@LP@:-f,@LF@:./,@LD@:E:/wkspace/wi2017i/id2/mike/lmz/target/lmz/scripts/test,@LA@:";
		//args[0]="@LP@:,@LF@:./test.lmz,@LD@:E:/wkspace/wi2017i/id2/mike/lmz/target/lmz/scripts/test,@LA@:";
		Lmz lmz = new Lmz();
		lmz.execute(args);

	}

}
