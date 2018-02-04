package cn.lmz.mike.common.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class ZipU {

	static final int BUFFER = 8192;

	public static String compress(String inName) {
		return compress(inName,null);
	}
	public static String compress(String inName,String outFile) {
		File file = new File(inName);
		if (!file.exists())
			throw new RuntimeException(inName + "不存在！");
		if(outFile==null){
			outFile = inName.substring(0,inName.lastIndexOf("."))+".zip";
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(outFile);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			String basedir = "";
			compress(file, out, basedir);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return outFile;
	}

	private static void compress(File file, ZipOutputStream out, String basedir) {
		if (file.isDirectory()) {
			compressDirectory(file, out, basedir);
		} else {
			compressFile(file, out, basedir);
		}
	}

	private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	private static void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	


	public static void main(String[] args){
		String name="D:\\reportCMD\\output\\20140901_审核部日报表1.xls";
		ZipU.compress(name);
	}

}
