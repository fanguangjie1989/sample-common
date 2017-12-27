package com.cnioc.sample.common.jvm;

import java.io.Serializable;

/**
 * 
 * <p>
 * Title:OverLoad
 * </p>
 * <p>
 * Desc:重载方法匹配优先级 
 * 1、char->int->long->float->double
 * 2、装箱后 Character 遵循继承优先级越往上优先级越
 * </p>
 * 
 * @author fangj
 * @date 2017年12月27日 下午2:51:47
 */
public class OverLoad {
	public static void sayHello(Object arg) {
		System.out.println(" hello object");
	}

	public static void sayHello(int arg) {
		System.out.println(" hello int");
	}

	public static void sayHello(long arg) {
		System.out.println(" hello long");
	}

	public static void sayHello(Character arg) {
		System.out.println(" hello character");
	}
	
	public static void sayHello(char arg){
		System.out.println(" hello char ");
	}

	public static void sayHello(char... arg) {
		System.out.println(" hello char ...");
	}

	public static void sayHello(Serializable arg) {
		System.out.println(" hello serializable");
	}

	public static void sayHello(String[] arg) {
		System.out.println(" hello string[]");
	}
	
	public static void main(String[] args) {
		sayHello('a');
	}
}
