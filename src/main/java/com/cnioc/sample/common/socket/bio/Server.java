package com.cnioc.sample.common.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * <p>
 * Title:Server
 * </p>
 * <p>
 * Desc:Block IO Server 阻塞IO模型服务端
 * 同步阻塞
 * <br/>
 * 1、每次建立链接需要启动一个新的线程去处理链接和客户端通信
 * </p>
 * 
 * @author fangj
 * @date 2017年12月20日 上午9:07:02
 */
public class Server {
	/**
	 * 服务端端口号
	 */
	private static final int PORT = 8765;
	/**
	 * 计数器
	 */
	private static AtomicLong counter = new AtomicLong(0L);
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println(" server started ...");
			while (true) {
				// 进行阻塞
				Socket socket = serverSocket.accept();
				//启动一个线程去处理已经连接的嵌套字(客户端连接)
				new Thread(new ServerHandler(socket,counter.getAndIncrement())).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			serverSocket = null;
		}
	}
}
