package com.cnioc.sample.common.jvm;
/**
 * 
 * <p>Title:DynamicDispatch</p>
 * <p>Desc:
 * 动态分派:根据实际类型来确定方法执行版本分派过程<br/>
 * JVM如何根据实际类型确定执行的版本?
 * <br/>
 * invokevirtual指令的多态查找过程
 * 1.找到操作数栈顶的第一个元素所指向的对象的实际类型，记作C <br/>
 * 2.如果在类型C中找到了与常量中描述符和简单名称都相等的方法则执行访问权限校验如果通过则返回这个方法的直接引用，查找结束
 * 如果不通过则返回java.lang.IllegalAccessError <br/>
 * 3.否则，按照继承关系从下向上一次对C的各个父类进行第二部的搜索过程 <br/>
 * 4.如果最后还没找到直接抛出java.lang.AbstractMethodError<br/>
 * 
 * </p>
 * @author fangj
 * @date 2017年12月27日 下午3:08:16
 */
public class DynamicDispatch {
	static abstract class Human{
		protected abstract void sayHello();
	}
	
	static class Man extends Human{
		@Override
		protected void sayHello() {
			System.out.println(" hello man");
		}
	}
	
	static class Women extends Human{
		@Override
		protected void sayHello() {
			System.out.println(" hello women");
		}
	}
	
	public static void main(String[] args) {
		Human man = new Man();
		Human women = new Women();
		man.sayHello();
		women.sayHello();
		man = new Women();
		man.sayHello();
	}
}
/**
 hello man
 hello women
 hello women
 */
