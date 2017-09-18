package com.viki.java.nio.channels;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest extends FileLock {

	protected FileLockTest(FileChannel channel, long position, long size, boolean shared) {
		super(channel, position, size, shared);
	}

	private static FileLockTest fl;

	/*static {
		RandomAccessFile aFile;
		try {
			aFile = new RandomAccessFile("G:\\范辉\\0505测4试.txt", "rw");
			FileChannel inChannel = aFile.getChannel();
			fl = new FileLockTest(inChannel, 0, (int)aFile.length(), false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	public static void main(String[] args) throws IOException {
		try {
			RandomAccessFile aFile = new RandomAccessFile("G:\\范辉\\0505测试7.txt", "rw");
			FileChannel inChannel = aFile.getChannel();
			fl = new FileLockTest(inChannel, 0, (int)aFile.length(), false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sizeTest();
	}

	/**
	 * 返回文件通道，此锁定保持在该通道的文件上。
	 * 
	 */
	public static void channelTest() {
		System.out.println(fl.channel());
	}

	public static void toStringTest() {
		System.out.println(fl);
	}

	public static void sizeTest() throws IOException {
		FileChannel fc = fl.channel();
		ByteBuffer bb = ByteBuffer.allocate((int)fc.size());
		try {
			while(fc.read(bb) != -1){
				bb.flip();
				System.out.println(new String(bb.array(),"UTF-8"));
				 bb.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		fc.close();
	}

	@Override
	public boolean isValid() {
		return false;
	}

	@Override
	public void release() throws IOException {
	}
	

}
