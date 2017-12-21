package com.cnioc.sample.common.socket.bio2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 
 * <p>Title:HandlerExecutorPool</p>
 * <p>
 * Desc: 线程池  ThreadPoolExecutor:<br/>
 * 1.当线程池小于corePoolSize时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。 <br/>
 * 2.当线程池达到corePoolSize时，新提交任务将被放入workQueue中，等待线程池中任务调度执行 <br/>
 * 3.当workQueue已满，且maximumPoolSize>corePoolSize时，新提交任务会创建新线程执行任务 <br/>
 * 4.当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理 <br/>
 * 5.当线程池中超过corePoolSize线程，空闲时间达到keepAliveTime时，关闭空闲线程 <br/>
 * 6.当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭<br/> 
 * 
 * </p>
 * @author fangj
 * @date 2017年12月20日 上午10:04:43
 */
public class HandlerExecutorPool{

	private ExecutorService executor;
	private ThreadFactory threadFactory;
	
	/**
	 * 
	 * @param maxPoolSize   当前可以创建最大线程数
	 * @param queueSize     对列容量
	 * @param threadFactory 线程工厂
	 */
	public HandlerExecutorPool(int maxPoolSize, int queueSize,ThreadFactory threadFactory){
		this.threadFactory =  threadFactory;
		this.executor = new ThreadPoolExecutor(
				Runtime.getRuntime().availableProcessors(),
				maxPoolSize, 
				10L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(queueSize),threadFactory,new ThreadPoolExecutor.AbortPolicy());
	}
	
	public void execute(Runnable task){
		this.executor.execute(threadFactory.newThread(task));
	}
	
	public void shutdown(){
		this.executor.shutdown();
	}
}
