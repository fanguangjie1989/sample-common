package com.cnioc.sample.common.socket.bio2;

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
 * Desc:Block IO Server 阻塞IO模型服务端<br/>
 * 同步阻塞<br/>
 * 线程池处理：<br/>
 * 使用线程池的好处是减少在创建和销毁线程上所花的时间以及系统资源的开销，解决资源不足的问题。<br/>
 * 如果不使用线程池，有可能造成系统创建大量同类线程而导致消耗完内存或者“过度切换”的问题。<br/>
 * 阻塞IO
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
	 * 计数器给每一个客户端一个唯一的编号
	 */
	private static AtomicLong counter = new AtomicLong(0L);
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		HandlerExecutorPool handlerExecutorPool = null;
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println(" server started ...");
			handlerExecutorPool = new HandlerExecutorPool(50, 100,new HandlerThreadFactoryBuilder());
			while (true) {
				// 进行阻塞
				Socket socket = serverSocket.accept();
				//使用线程池去处理已经连接的嵌套字(客户端连接)
				handlerExecutorPool.execute(new ServerHandler(socket, counter.getAndIncrement()));
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
			
			if(handlerExecutorPool != null){
				handlerExecutorPool.shutdown();
			}
			
			handlerExecutorPool = null;
		}
	}
}
