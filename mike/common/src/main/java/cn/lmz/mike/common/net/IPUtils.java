package cn.lmz.mike.common.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author fengliming
 *
 */
public class IPUtils {

	private static Logger logger = LoggerFactory.getLogger(IPUtils.class);

	public static String getInet4Address() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress tempIp = (InetAddress) addresses.nextElement();
					if (tempIp != null && tempIp instanceof Inet4Address) {
						if (!"127.0.0.1".equals(tempIp.getHostAddress())
								&& !"localhost".equals(tempIp.getHostAddress())) {
							ip = tempIp;
							break;
						}
					}
				}
				if (ip != null)
					break;
			}

			if (ip != null)
				return ip.getHostAddress();
		} catch (Exception e) {
			logger.error("获取IP地址失败");
		}
		return "0.0.0.0";
	}

	public static void main(String[] args) {
		System.out.println(IPUtils.getInet4Address());
	}
}
