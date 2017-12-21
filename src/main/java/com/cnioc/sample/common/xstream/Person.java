package com.cnioc.sample.common.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 
 * <p>Title:Person</p>
 * <p>Desc:</p>
 * @author fangj
 * @date 2017年12月18日 下午5:13:40
 */
@XStreamAlias("person")
public class Person {
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("age")
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
}
