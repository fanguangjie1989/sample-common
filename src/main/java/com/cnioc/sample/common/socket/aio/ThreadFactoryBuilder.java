package com.cnioc.sample.common.socket.aio;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
 * <p>Title:HandlerThreadFactoryBuilder</p>
 * <p>
 * Desc:处理器线程创建工厂
 * <br/>
 * 创建线程或线程池时请指定有意义的线程名称，方便出错时回溯
 * </p>
 * @author fangj
 * @date 2017年12月20日 上午10:36:06
 */
public class ThreadFactoryBuilder implements ThreadFactory {
	/**
	 * 线程池编号
	 */
	private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
	
	private final ThreadGroup group;
	
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	
	private final String namePrefix;
	
	public ThreadFactoryBuilder(ThreadGroup group, String prefix) {
		if (group == null) {
			SecurityManager s = System.getSecurityManager();
			this.group = (s != null)
					? s.getThreadGroup()
					: Thread.currentThread().getThreadGroup();
		} else {
			this.group = group;
		}
		if (prefix == null) {
			this.namePrefix = "pool-" + POOL_NUMBER.getAndIncrement()
					+ "-thread-";
		} else {
			this.namePrefix = prefix + "-" + POOL_NUMBER.getAndIncrement()
					+ "-thread-";
		}
	}
	
	public ThreadFactoryBuilder(String prefix){
		this(null, prefix);
	}
	
	public ThreadFactoryBuilder() {
		this(null, null);
	}
	
	
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(this.group, r,this.namePrefix + this.threadNumber.getAndIncrement(), 0);
		return t;
	}

}
