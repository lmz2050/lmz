package cn.lmz.mike.data.aspect;

import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.support.IEntity;
import cn.lmz.mike.data.util.define.DataSV;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

public class DataAspect {

	@Pointcut("execution(* org.lmz.base.data.Entity.create(..))")
	public void create(){}
	@Pointcut("execution(* org.lmz.base.data.Entity.delete(..))")
	public void delete(){}
	@Pointcut("execution(* org.lmz.base.data.Entity.update(..))")
	public void update(){}
	
	@Around("create()")
	public void create(ProceedingJoinPoint jp) throws Throwable{
		 doDL(jp, DataSV.INSERT,(IEntity)jp.getTarget());
	}

	@Around("delete()")
	public void delete(ProceedingJoinPoint jp) throws Throwable{
		doDL(jp,DataSV.DELETE,(IEntity)jp.getTarget());
	}

	@Around("update()")
	public void update(ProceedingJoinPoint jp) throws Throwable{
		doDL(jp,DataSV.UPDATE,(IEntity)jp.getTarget());
	}
	
	public void doDL(ProceedingJoinPoint jp,Integer op,IEntity en) throws Throwable{
		System.out.println("data aspect......");
		jp.proceed();
		Object arg = jp.getArgs()[0];
		 if(arg instanceof DataBean){
			 arg = arg.getClass();
		 }

		//DataL.doDL((Class<?>)arg, op,en);
	}
}
