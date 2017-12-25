package com.cnioc.sample.common.jvm;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * <p>Title:MethodAreaOOM</p>
 * <p>Desc:方法区OOM
 * <br/>
 * 1. VM args: -XX:PermSize=10M -XX:MaxPermSize=10M:设置持久代大小
 * </p>
 * 回收方法区<br/>
 * 1.无用类：没有对应的实例对象，加载该类的类加载器被回收....<br/>
 * 
 * @author fangj
 * @date 2017年12月21日 下午3:25:53
 */
public class MethodAreaOOM {
	public static void main(String[] args) {
		while(true){
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(HeapOOM.OOMObject.class);
			enhancer.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					return proxy.invokeSuper(obj, args);
				}
			});
			enhancer.create();
		}
	}
}
