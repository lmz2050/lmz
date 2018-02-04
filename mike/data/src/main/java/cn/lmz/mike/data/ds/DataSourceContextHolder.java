package cn.lmz.mike.data.ds;

public class DataSourceContextHolder {
	
	public static final String DATA_SOURCE_SYS = "dataSourceSys";
	public static final String DATA_SOURCE_BUS = "dataSourceBus";
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDataSourceType(String dataSourceType) {
		contextHolder.set(dataSourceType);
	}

	public static String getDataSourceType() {
		return contextHolder.get();
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}
}
