package cn.lmz.mike.common.cmmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class SSH {

	public static void main(String[] args) {
	    String hostname = "172.16.5.229";
	    String username = "bqadm";
	    String password = "slsuaT@bqjr";
	    
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append("-----BEGIN RSA PRIVATE KEY-----");
	    sb.append("MIIEowIBAAKCAQEAw8uIhgSVsXIw79fzJ1V/ghthzLIk751D6DtTPH6uCkL4tBe3");
	    sb.append("SO+O3Pj8D+yr8rPjTjfSyw3Ol61TA2LHE6Sn7uvUAOnPVW4Kyc7w8Ux1AzyJBu8R");
	    sb.append("cHqhJzjvNWFdYRnITHdhzEKA+gvTMA6kA+mHgBBsjXsbxM39skEJ8K63F530979U");
	    sb.append("gibbe3Zx+420jUX9YNNKKRo8Yow7EFSsrN1gdZKJdAFdmzJkssSBnnJQhcl+yq+c");
	    sb.append("Hx7WcBPc7pEjQ/BzhmIpaofC31KMZEpRz8kVP4acT20PTBWxFlKlnjpvSZGdozEE");
	    sb.append("2tK9wKG7d3lsHRUubsONMAdCIgi7a1OmRNdN6wIDAQABAoIBAGC0/R/Ez/MvRcCR");
	    sb.append("V0ZMUoh+4dVrABdiBBKGitxMbcYfwhuhwyPBPz0htc5fxaZA97rAk9s7XJlKQMBh");
	    sb.append("X5byMwgtby9sTL4TdjyrfBKTAxCwK7ZDCsgVqddnpa8mAitdwrMSHDn5dx8KDCum");
	    sb.append("b5iHFihI9HUFI3gXjoEm4rQAd+qveUmsDyjabyK+CfNOUfC3/TDgnHsM9mQxtllb");
	    sb.append("b5Ywt1mt+7C9pa7m+JqerWFagWj84UBgNdD5qNx0esEmxzt3L2iEH5Z5N8nMmwie");
	    sb.append("qvf/DanZsb7iYVxtEZHEptuCp3+16Fb0owZvMyCAE5T+n3GGGQO7QXR+bBkjv/jB");
	    sb.append("OsVY8mECgYEA/hZVG1C/lBJMKFy1My1SkjCQerEe27V5SaokO8a8neuD73kH2X6t");
	    sb.append("00g5dHTvh0zfAMLFlRbBFRjCGBsUglXVwBTlIfoF4tIM4QuTC07D5wVgxDStMa3s");
	    sb.append("ExvS9OrJoUvHfR3IUzG8D52+j7mFgjPEtNem54n32xaS7cQDLlkhCNkCgYEAxUTc");
	    sb.append("v6GY8dzPhgPyUyowZPcttykTI60WyEAacqA+3Q+7lRpcbtVBrm2R5nUaDgvuCJAm");
	    sb.append("t1o9YP+GGR5SQGV0+ma4fBoPd8sO/7CE4qUxnRD7IBVm/MVq0LaUDsekmQRXsJen");
	    sb.append("eOZ1DHC2wXliTELTuJtlbZQ4LKK4bprDCRn6smMCgYA5MovMD1xhF9FfEKeVsrEw");
	    sb.append("yY/snqykpvB7vbpUXoOdRSQ5J1S2CEdXFZ4hciVtnsdgo3fY/PtQJXej94yhXnM0");
	    sb.append("vz9pa6AhlElj67gZRozhF5vV70nBsjY/gvb1PVlQXv+0UZ0D5WFBXrBEKG4+hirU");
	    sb.append("zur6mltaTXWb4pEYwOWn2QKBgQClIXvYPiGVdM2S1YLn8w0Ht5HhHNUUrDPB9Ucu");
	    sb.append("ZM26pwzTR3nQnVuZ88aKL8RbtcjQCjseiL+QjtVl+WfI2CUP5yTPXdFM6gHoL49N");
	    sb.append("WhMp86kDtma7eVZs2CkgJia2oFf3Tzu1jNS2UTPqyP1r8RTiF9LT45sSYjQZAhv5");
	    sb.append("ZnHhGQKBgDTenSQ92XLWvt0+TbUd2MaHdImYob7mtNjVoXThzkIeLhJbe5j3aGxC");
	    sb.append("TgcOTMSkF6vPFQ0gms1uSDdbk7oXrmjGMSf3PvMHidhMFEiw1/irI5DEccHt81gU");
	    sb.append("lpEu8srlKVyBPzkzwbzl++hqou+HjQxh5LVGuN157Ra7tlEd8Pve");
	    sb.append("-----END RSA PRIVATE KEY-----");
	    
	    
	    
	    try {
	      Connection conn = new Connection(hostname);
	      conn.connect();
	      //boolean isAuthenticated = conn.authenticateWithPassword(username, password);
	      boolean isAuthenticated = conn.authenticateWithPublicKey(username, sb.toString().toCharArray(),null);
	      
	      if (!isAuthenticated)
	        throw new IOException("Authentication failed.");
	      Session sess = conn.openSession();
	      InputStream stdout = new StreamGobbler(sess.getStdout());
	      InputStream stderr = new StreamGobbler(sess.getStderr());

	      BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));

	      sess.execCommand("tail -f /home/bqadm/apphome/logs/rmps-service-dcn-3.3.0-SNAPSHOT-20101.log");

	      System.out.println("Here is the output from stdout:");

	      byte[] bytes = new byte[1024];
	      int index = 0;
	      while ((index = stdout.read(bytes)) > 0) {
	        System.out.write(bytes, 0, index);
	      }

	      System.out.println("Here is the output from stderr:");
	      while (true) {
	        String line = stderrReader.readLine();
	        if (line == null)
	          break;
	        System.out.println(line);
	      }

	      stdout.close();
	      if (stderrReader != null) {
	        stderrReader.close();
	      }
	      sess.close();

	      conn.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	}

}
