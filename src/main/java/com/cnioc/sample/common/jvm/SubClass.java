package com.cnioc.sample.common.jvm;
/**
 * 
 * <p>Title:SubClass</p>
 * <p>Desc:</p>
 * @author fangj
 * @date 2017年12月26日 下午3:11:29
 */
public class SubClass extends SupperClass {
	public static final String HELLO_WORLD = "hello world";
	static {
		System.out.println(" SubClass init");
	}
}
