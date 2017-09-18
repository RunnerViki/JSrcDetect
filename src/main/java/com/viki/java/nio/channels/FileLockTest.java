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
			aFile = new RandomAccessFile("G:\\����\\0505��4��.txt", "rw");
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
			RandomAccessFile aFile = new RandomAccessFile("G:\\����\\0505����7.txt", "rw");
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
	 * �����ļ�ͨ���������������ڸ�ͨ�����ļ��ϡ�
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
