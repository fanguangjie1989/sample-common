package com.cnioc.sample.common.jvm;
/**
 * 
 * <p>Title:StackOOM</p>
 * <p>Desc:Stack OOM
 * 
 * </p>
 * @author fangj
 * @date 2017年12月21日 下午2:28:47
 */
public class StackOOM {
	public void stackLeakByThread(){
		while(true){
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						
					}
				}
			}).start();
		}
	}
	public static void main(String[] args) {
		StackOOM soom = new StackOOM();
		soom.stackLeakByThread();
	}
}
