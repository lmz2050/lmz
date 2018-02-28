package cn.lmz.mike.data.cache.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CacheListener implements ServletContextListener {

	private static final Logger log = LoggerFactory.getLogger(CacheListener.class);

	public void contextInitialized(ServletContextEvent event)
	{
		String nameL = "";
		try{
			String cacheXml = event.getServletContext().getInitParameter("cacheLocation");
			String cacheLocation = event.getServletContext().getRealPath(cacheXml);
			log.info("cacheLocation:"+cacheLocation);
			
			CacheU.setCacheLocation(cacheLocation);
			CacheU.flush();
			
		}catch(Exception e){
			log.error("LmzListener.contextInitialized(ServletContextEvent event)["+nameL+"]"+e);
		}
	}
	
	public void contextDestroyed(ServletContextEvent event)
	{
		
	}
}