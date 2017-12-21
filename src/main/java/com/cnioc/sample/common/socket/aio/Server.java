package com.cnioc.sample.common.socket.aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 
 * <p>Title:Server</p>
 * <p>Desc:
 * AIO 异步IO server
 * </p>
 * @author fangj
 * @date 2017年12月20日 下午3:05:41
 */
public class Server {
	/**
	 * 线程池
	 */
	private ExecutorService executorService;
	/**
	 * 线程组
	 */
	private AsynchronousChannelGroup threadGroup;
	/**
	 * 服务器通道
	 */
	public AsynchronousServerSocketChannel assc;
	
	public Server(int port){
		try {
			//创建一个缓存池
			executorService = new ThreadPoolExecutor(
					Runtime.getRuntime().availableProcessors(),
					50, 
					10L,
					TimeUnit.SECONDS,
					new LinkedBlockingQueue<Runnable>(100),new ThreadFactoryBuilder(),new ThreadPoolExecutor.AbortPolicy());
			
			//创建线程组
			threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
			
			//创建服务器通道
			assc = AsynchronousServerSocketChannel.open(threadGroup);
			
			//进行绑定
			assc.bind(new InetSocketAddress(port));
			
			System.out.println("server start , port : " + port);
			//进行阻塞
			assc.accept(this, new ServerCompletionHandler());
			//一直阻塞 不让服务器停止
			Thread.sleep(Integer.MAX_VALUE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server(8765);
	}
	
}
