package com.ztools.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtilly {

	/**
	 * 使用管道向管道传输文件 fi to f2
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 * @throws Exception
	 */
	public static boolean forChannel(File f1, File f2)  {
		boolean re = false;
		FileInputStream in =null;
		FileOutputStream out =null;
		FileChannel inC  =null;
		FileChannel outC =null;
		try {
			int length = 2097152;
			in= new FileInputStream(f1);
			out = new FileOutputStream(f2);
			inC= in.getChannel();
			outC = out.getChannel();
			ByteBuffer b = null;
			while (true) {
				if (inC.position() == inC.size()) {
					inC.close();
					outC.close();
					inC = null;
					outC = null;
					re = true;
					return re;
				}
				if ((inC.size() - inC.position()) < length) {
					length = (int) (inC.size() - inC.position());
				} else
					length = 2097152;
				b = ByteBuffer.allocateDirect(length);
				inC.read(b);
				b.flip();
				outC.write(b);
				outC.force(false);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(inC!=null){
					inC.close();
				}
				if(outC!=null){
					outC.close();
				}
				if(in!=null){
					in.close();
				}
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}
}
