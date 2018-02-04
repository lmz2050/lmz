package cn.lmz.mike.data.aspect;

import cn.lmz.mike.common.log.O;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.cache.base.SyncU;
import cn.lmz.mike.data.util.define.DataSV;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

public class CacheAspect {

	@Pointcut("execution(* org.lmz.base.data.cache.CacheDao.create(org.lmz.base.bean.BaseBean))")
	public void create(){} 
	@Pointcut("execution(* org.lmz.base.data.cache.CacheDao.delete(org.lmz.base.bean.BaseBean))") 
	public void delete(){} 
	@Pointcut("execution(* org.lmz.base.data.cache.CacheDao.update(org.lmz.base.bean.BaseBean))")
	public void update(){} 
	

	@Around("create()")
	public void create(ProceedingJoinPoint jp) throws Throwable{
		 addSync(jp, DataSV.INSERT);
	}

	@Around("delete()")
	public void delete(ProceedingJoinPoint jp) throws Throwable{
		 addSync(jp,DataSV.DELETE);
	}

	@Around("update()")
	public void update(ProceedingJoinPoint jp) throws Throwable{
		 addSync(jp,DataSV.UPDATE);
	}
	

	public void addSync(ProceedingJoinPoint jp,Integer op) throws Throwable{
		 O.info("aspect...................."+op);
		 jp.proceed();
		 if(jp.getArgs().length==1){
			 DataBean b = (DataBean)jp.getArgs()[0];
			 SyncU.addCync(b,op);
		 }		 
	}
	
}
