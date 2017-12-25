package com.cnioc.sample.common.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Title:RuntimeConstantPoolOOM</p>
 * <p>Desc:运行时常量池内存溢出
 * <br/>
 * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M:设置持久代大小
 * <br/>
 * </p>
 * @author fangj
 * @date 2017年12月21日 下午2:47:08
 */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i = 0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}
}
