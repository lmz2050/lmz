package cn.lmz.mike.data.cache.base;


import cn.lmz.mike.common.log.O;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CacheListener implements ServletContextListener {


	public void contextInitialized(ServletContextEvent event)
	{
		String nameL = "";
		try{
			String cacheXml = event.getServletContext().getInitParameter("cacheLocation");
			String cacheLocation = event.getServletContext().getRealPath(cacheXml);
			O.info("cacheLocation:"+cacheLocation);
			
			CacheU.setCacheLocation(cacheLocation);
			CacheU.flush();
			
		}catch(Exception e){
			O.error("LmzListener.contextInitialized(ServletContextEvent event)["+nameL+"]"+e);
		}
	}
	
	public void contextDestroyed(ServletContextEvent event)
	{
		
	}
}