package com.cnioc.sample.common.jvm;

/**
 * 
 * <p>
 * Title:ReferenceCountingGC
 * </p>
 * <p>
 * Desc:JVM GC 
 * 
 * 哪些对象需要回收
 * 
 * 引用计数算法GC缺陷<br/>
 * 引用计数法：实现简单，判断效率高 eg:paython <br/>
 * VM args : -XX:+PrintGC<br/>
 * <br/>
 * java 可达性分析算法 <br/>
 * GCRoot 作为原始点 从原始点开始向下搜索，搜索所经过的路径称为引用链<br/>
 * GCRoot:<br/>
 * 1.虚拟机栈中引用的对象<br/>
 * 2.方法区中类属性引用的对象<br/>
 * 3.方法区中常量引用的对象<br/>
 * 4.本地方法栈中JNI引用的对象<br/>
 * 
 * 引用分类<br/>
 * 1.强：ReferenceCountingGC objA = new ReferenceCountingGC()引用存在不能回收<br/>
 * 2.软：有用，非必要的对象  ;内存溢出之前,回收<br/>
 * 3.弱: 非必需的对象 ;引用强度弱于软引用,下一次垃圾回收到来回收<br/>
 * 4.虚: 通过引用无法获取对象，对象被GC回收时收到一个系统通知<br/>
 * 
 * 垃圾回收算法<br/>
 * 1.标记-清除 Mark Sweep  :标记清理效率不高，内存不连续<br/>
 * 2.复制算法 Copying      :内存分为大小相等的两块 一个，一块用完就将活着的对象移动到另一块上，将当前的清空。<br/>
 * 3.标记-整理Mark Compact:所有存货的对象向一端移动，然后直接清理掉边界以外的内存<br/>
 * 4.分代收集算法 ：新生代少量对象存活，复制算法。老生代：存活率高，标记整理，标记清理算法<br/>
 * 
 * </p>
 * 
 * @author fangj
 * @date 2017年12月21日 下午3:53:56
 */
public class ReferenceCountingGC {
	public Object instance = null;
	private static final int DATA = 1024 * 1024;
	@SuppressWarnings("unused")
	private byte[] bigSize = new byte[2 * DATA];

	public static void main(String[] args) {
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		objA.instance = objB;
		objB.instance = objA;
		objA = null;
		objB = null;
		System.gc();
	}
}
/** -XX:+PrintGC */
/**
 * [GC (System.gc()) 6092K->600K(125952K), 0.0365844 secs] [Full GC
 * (System.gc()) 600K->536K(125952K), 0.0183433 secs]
 */

/** -XX:+PrintGCDetails */
/**
 * [GC (System.gc()) [PSYoungGen: 6092K->600K(38400K)] 6092K->608K(125952K),
 * 0.0499012 secs] [Times: user=0.00 sys=0.00, real=0.05 secs] [Full GC
 * (System.gc()) [PSYoungGen: 600K->0K(38400K)] [ParOldGen: 8K->536K(87552K)]
 * 608K->536K(125952K), [Metaspace: 2665K->2665K(1056768K)], 0.0709336 secs]
 * [Times: user=0.02 sys=0.00, real=0.07 secs] Heap PSYoungGen total 38400K,
 * used 333K [0x00000000d5c00000, 0x00000000d8680000, 0x0000000100000000) eden
 * space 33280K, 1% used
 * [0x00000000d5c00000,0x00000000d5c534a8,0x00000000d7c80000) from space 5120K,
 * 0% used [0x00000000d7c80000,0x00000000d7c80000,0x00000000d8180000) to space
 * 5120K, 0% used [0x00000000d8180000,0x00000000d8180000,0x00000000d8680000)
 * ParOldGen total 87552K, used 536K [0x0000000081400000, 0x0000000086980000,
 * 0x00000000d5c00000) object space 87552K, 0% used
 * [0x0000000081400000,0x0000000081486200,0x0000000086980000) Metaspace used
 * 2671K, capacity 4486K, committed 4864K, reserved 1056768K class space used
 * 288K, capacity 386K, committed 512K, reserved 1048576K
 */
