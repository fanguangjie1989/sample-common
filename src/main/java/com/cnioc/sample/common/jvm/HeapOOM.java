package com.cnioc.sample.common.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Title:HeapOOM</p>
 * <p>
 * Desc:java 堆内存溢出异常测试,<br/>
 * -Xms:初始堆大小<br/>
 * -Xmx:最大堆大小<br/>
 * XX:+HeapDumpOnOutOfMemoryError可以让JVM在探测到内存OOM的时候打印dump<br/>
 * -XX:HeapDumpPath=${} 参数表示生成DUMP文件的路径，也可以指定文件名称<br/>
 * VM args : -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\tmp
 * </p>
 * 
 * @author fangj
 * @date 2017年12月21日 上午9:45:57
 */
public class HeapOOM {
	
	static class OOMObject{
		int[] array = new int[1*1024*1024];
	}
	
	public static void main(String[] args) {
		List<OOMObject> oomObjects =  new ArrayList<OOMObject>();
		while(true){
			oomObjects.add(new OOMObject());
		}
	}
}
/**
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * at java.util.Arrays.copyOf(Arrays.java:3210)
 * at java.util.Arrays.copyOf(Arrays.java:3181)
 * at java.util.ArrayList.grow(ArrayList.java:261)
 * at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
 * at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
 * at java.util.ArrayList.add(ArrayList.java:458)
 * at com.cnioc.sample.common.jvm.HeapOOM.main(HeapOOM.java:27)
 */
