package com.cnioc.sample.common.jvm;
/**
 * 
 * <p>
 * Title:MemoryAllocation
 * </p>
 * <p>
 * Desc: JVM内存分配策略 <br/>
 * 1.VM args: -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 * <br/>
 * -XX:PretenureSizeThreshold 大于该参数的对象直接分配到老生代
 * <br/>
 * -XX:MaxTenuringThreshold=8    参数用于设定对象最大年龄阈值
 * </p>
 * 
 * @author fangj
 * @date 2017年12月22日 下午2:33:38
 */
public class MemoryAllocation {
	private static final int MB = 1024 * 1024;

	public static void main(String[] args) {
		//byte[] allo1 = new byte[1 * MB];
		//byte[] allo2 = new byte[2 * MB];
		//下面直接分配到老生代 line41
		byte[] allo3 = new byte[4 * MB];
		byte[] allo4 = new byte[4 * MB];
	}
}
/**

*/
