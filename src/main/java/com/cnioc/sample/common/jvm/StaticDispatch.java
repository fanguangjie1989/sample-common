package com.cnioc.sample.common.jvm;
/**
 * 
 * <p>Title:StaticDispatch</p>
 * <p>Desc:
 * 静态分派（方法重载本质）<br/>
 * 静态分派发生在编译期间，编译器根据参数的静态类型来确定方法重载的版本<br/>
 * 确定的版本不唯一时取最合适的版本<br/>
 * 静态类型 实际类型<br/>
 * </p>
 * @author fangj
 * @date 2017年12月27日 下午2:14:52
 */
public class StaticDispatch {
	static abstract class Human{}
	static class Man extends Human{}
	static class Women extends Human{}
	
	public static void sayHello(Human guy){
		System.out.println(" hello,guy");
	}
	
	public static void sayHello(Man guy){
		System.out.println(" hello,man");
	}
	
	public static void sayHello(Women guy){
		System.out.println(" hello,women");
	}
	
	public static void main(String[] args) {
		//Human 静态类型 外观类型
		//Man   实际类型
		Human man =new Man();
		Human women  = new Women();
		sayHello(man);
		sayHello(women);
		sayHello((Man)man);
		sayHello((Women)women);
	}
	
}
/**
 hello,guy
 hello,guy
 hello,man
 hello,women
**/
