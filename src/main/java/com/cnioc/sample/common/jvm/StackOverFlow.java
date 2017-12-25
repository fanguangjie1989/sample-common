package com.cnioc.sample.common.jvm;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * <p>Title:StackOverFlow</p>
 * <p>Desc:虚拟机栈溢出测试
 * 1.栈深度大于虚拟机允许的最大深度<br/>
 * 2.虚拟机在扩展栈时无法申请到足够的空间<br/>
 * -Xxs128k
 * </p>
 * @author fangj
 * @date 2017年12月21日 下午2:02:09
 */
public class StackOverFlow {
	
	private AtomicLong stackLength =new AtomicLong(1L);

	public long getStackLength() {
		return stackLength.get();
	}
	
	public void stackLeak(){
		stackLength.getAndIncrement();
		stackLeak();
	}
	
	public static void main(String[] args) {
		StackOverFlow sof = new StackOverFlow();
		try {
			sof.stackLeak();
		} catch (Exception e) {
			throw e;
		}finally{
			System.out.println("stack length :"+sof.getStackLength());
		}
	}
	
}
/**Exception in thread "main" java.lang.StackOverflowError*/
/**The stack size specified is too small, Specify at least 108k*/