package com.cnioc.sample.common.jvm;
/**
 * 
 * <p>Title:SupperClass</p>
 * <p>Desc:
 * 被动使用类字段演示
 * <br/>
 * 1.通过子类引用父类的静态字段，不会导致类初始化
 * </p>
 * @author fangj
 * @date 2017年12月26日 下午3:09:00
 */
public class SupperClass {
	
	public static int value = 2;
	
	static{
		System.out.println(" SupperClass init");
	}
}
