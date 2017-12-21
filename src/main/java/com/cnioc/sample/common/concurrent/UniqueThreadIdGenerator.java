package com.cnioc.sample.common.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
 * <p>Title:UniqueThreadIdGenerator</p>
 * <p>Desc:线程唯一ID生成器,ThreadLocal源码</p>
 * <p>
 * 1、在每个线程Thread内部有一个ThreadLocal.ThreadLocalMap类型的成员变量threadLocals，<br/>
 * 2、这个threadLocals就是用来存储实际的变量副本的，键值为当前ThreadLocal变量，value为变量副本（即T类型的变量）。<br/>
 * 3、初始时，在Thread里面，threadLocals为空，<br/>
 * 4、当通过ThreadLocal变量调用get()方法或者set()方法，就会对Thread类中的threadLocals进行初始化，<br/>
 * 5、并且以当前ThreadLocal变量为键值，以ThreadLocal要保存的副本变量为value，存到threadLocals。<br/>
 * 6、然后在当前线程里面，如果要使用副本变量，就可以通过get方法在threadLocals里面查找。
 * </p>
 * <p>
 * ThreadLocal与像synchronized这样的锁机制是不同的。<br/>
 * 1、锁更强调的是如何同步多个线程去正确地共享一个变量，<br/>
 * 2、ThreadLocal则是为了解决同一个变量如何不被多个线程共享<br/>
 * </p>
 * <p>
 * ThreadLocal的三个变量<br/>
 * 唯一的实例变量threadLocalHashCode是用来进行寻址的hashcode，它由函数nextHashCode()生成，
 * 该函数简单地通过一个增量HASH_INCREMENT来生成hashcode。
 * 至于为什么这个增量为0x61c88647，主要是因为ThreadLocalMap的初始大小为16，
 * 每次扩容都会为原来的2倍，这样它的容量永远为2的n次方，
 * 该增量选为0x61c88647也是为了尽可能均匀地分布，减少碰撞冲突。
 * </p>
 * @author fangj
 * @date 2017年12月18日 下午3:25:56
 * @see ThreadLocal
 */
public class UniqueThreadIdGenerator {
	/**
	 * Atomic integer containing the next thread ID to be assigned
	 */
    private static final AtomicInteger NEXT_ID = new AtomicInteger(0);

    /**
     * Thread local variable containing each thread's ID
     */
    private static final ThreadLocal<Integer> THREAD_ID =
        new ThreadLocal<Integer>() {
            @Override protected Integer initialValue() {
                return NEXT_ID.getAndIncrement();
        }
    };

    /**
     * Returns the current thread's unique ID, assigning it if necessary
     * @return
     */
    public static int get() {
        return THREAD_ID.get();
    }
    
    public static void main(String[] args) {
    	System.out.println(UniqueThreadIdGenerator.get());
	}
}
