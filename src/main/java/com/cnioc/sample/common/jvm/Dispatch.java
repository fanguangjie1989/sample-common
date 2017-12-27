package com.cnioc.sample.common.jvm;
/**
 * 
 * <p>Title:Dispatch</p>
 * <p>Desc:
 * 单分派多分派
 * </p>
 * @author fangj
 * @date 2017年12月27日 下午3:33:14
 */
public class Dispatch {
	static class QQ{}
	static class A360{}
	
	public static class Father{
		public void hardChoice(QQ arg){
			System.out.println(" father choose qq ");
		}
		public void hardChoice(A360 arg){
			System.out.println(" father choose a360 ");
		}
	}
	
	public static class Son extends Father{
		public void hardChoice(QQ arg){
			System.out.println(" Son choose qq ");
		}
		public void hardChoice(A360 arg){
			System.out.println(" Son choose a360 ");
		}
	}
	
	public static void main(String[] args) {
		Father father = new Father();
		Father son =  new Son();
		father.hardChoice(new A360());
		son.hardChoice(new QQ());
	}
}
