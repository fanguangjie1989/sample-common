package com.cnioc.sample.common.jvm;

import java.util.Map.Entry;
/**
 * 
 * <p>Title:StackTraceDemo</p>
 * <p>Desc:
 * jstack 生成虚拟机当前时刻的线程快照，每一个线程正在执行的方法堆栈集合
 * 定位线程出现长时间等待的原因，如线程时间死锁，死循环，请求外部资源导致长时间等待
 * </p>
 * @author fangj
 * @date 2017年12月26日 上午8:38:53
 */
public class StackTraceDemo {
	public static void main(String[] args) {
		/**
		 * 获取虚拟机中所有线程的StackTraceElement对象
		 */
		for (Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()) {
			Thread thread = stackTrace.getKey();
			StackTraceElement[]  stackTraceElements = stackTrace.getValue();
			if(thread.equals(Thread.currentThread())){
				continue;
			}
			System.out.println("线程 "+thread.getName());
			for (StackTraceElement stackTraceElement : stackTraceElements) {
				System.out.println(stackTraceElement);
			}
		}
	}
}
