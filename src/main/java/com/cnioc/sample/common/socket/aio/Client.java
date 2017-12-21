package com.cnioc.sample.common.socket.aio;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
 * <p>Title:Client</p>
 * <p>
 * Desc:
 * AIO 异步IO Client
 * </p>
 * @author fangj
 * @date 2017年12月20日 下午2:43:38
 */
public class Client implements Runnable{
	/**
	 * 为每个客户端生成一个编号
	 */
	private static final AtomicInteger CLIENT_NUM = new AtomicInteger(1);
	/**
	 * 客户端编号
	 */
	private final int clientNum = CLIENT_NUM.getAndIncrement();
	
	private AsynchronousSocketChannel asc ;
	
	public Client() throws Exception {
		asc = AsynchronousSocketChannel.open();
	}
	
	public void connect(){
		asc.connect(new InetSocketAddress("127.0.0.1", 8765));
	}
	
	public void write(String request){
		try {
			asc.write(ByteBuffer.wrap(request.getBytes())).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void read() {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		try {
			asc.read(buf).get();
			buf.flip();
			byte[] respByte = new byte[buf.remaining()];
			buf.get(respByte);
			System.out.println(new String(respByte,"utf-8").trim());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		System.out.println("I am thread <"+Thread.currentThread().getName()+"> client <"+clientNum+"> started ");
		try {
			Thread.sleep(1000);
			write("hello server I am client <"+clientNum+"> ");
			read();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Client c1 = new Client();
		c1.connect();
		
		Client c2 = new Client();
		c2.connect();
		
		Client c3 = new Client();
		c3.connect();
		
		new Thread(c1, "c1").start();
		new Thread(c2, "c2").start();
		new Thread(c3, "c3").start();
		
		Thread.sleep(1000);
	}
	
}
