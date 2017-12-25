package com.cnioc.sample.common.jvm;
/**
 * 
 * <p>Title:FinalizeEscapeGC</p>
 * <p>Desc:对象被回收时自我拯救<br/>
 * 1.任何一个对象的finalize方法只会被调用一次<br/>
 * 2.
 * </p>
 * @author fangj
 * @date 2017年12月21日 下午4:30:08
 */
public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOK = null;
	
	public void isAlive(){
		System.out.println("yes i am still a live");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println(" finalize executed ");
		SAVE_HOOK = this;
	}
	
	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeEscapeGC();
		//对象第一次成功拯救自己
		SAVE_HOOK = null;
		//GC
		System.gc();
		//GC finalize 执行级别很低 等待 1s
		Thread.sleep(1000);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println(" i am dead ");
		}
		
		//对象第二次拯救失败
		SAVE_HOOK = null;
		//GC
		System.gc();
		//GC finalize 执行级别很低 等待 1s
		Thread.sleep(1000);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println(" i am dead ");
		}		
	}
	
}
