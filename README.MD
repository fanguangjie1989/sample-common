##################common##############

rmi

	理解远程方法调用
	Remote Method Invoke    
	   
concurrent   

	理解java并发应用
	1、UniqueThreadIdGenerator 理解ThreadLocal,拜读ThreadLocal源码
	
xstream

         使用xstream
    1、XStream.toXML XStream.fromXML
    
socket
	
	socket通信
	概念理解：
	1.同步异步
	同步和异步一般是面向操作系统与应用程序对IO操作的层面来区别
	同步：同步时应用程序会直接参与IO读写操作，并且我们的应用程序会直接阻塞到某个方法上，直到数据准备就绪；或者采用轮训的策略实时检查数据的状态，如果就绪就获取数据
	异步：异步时，所有的IO读写操作交给操作系统处理，与我们的应用程序没有直接关系，我们的程序不需要关心IO读写，当操作系统完成了IO读写操作时，会给我们的应用程序发通知，我们的应用程序直接拿走数据即可。
	2.阻塞和非阻塞
	阻塞：应用程序在从网络中获取数据的时候，如果网络传输的数据很慢，那么应用程序会一直等待，直到传输完成。
	非阻塞：应用程序直接获取已经准备好的数据。
	
JVM
	Desc:JVM GC 
	
	GC算法：
	
	1.引用计数算法：引用计数法：实现简单，存在GC缺陷。判断效率高 eg:paython。
	2.可达性分析算法 :GCRoot 作为原始点 从原始点开始向下搜索，搜索所经过的路径称为引用链
	
	GCRoot:
	
	1.虚拟机栈中引用的对象
	2.方法区中类属性引用的对象
	3.方法区中常量引用的对象
	4.本地方法栈中JNI引用的对象

	引用分类
	
	1.强：ReferenceCountingGC objA = new ReferenceCountingGC()引用存在不能回收
	2.软：有用，非必要的对象  ;内存溢出之前,回收
	3.弱: 非必需的对象 ;引用强度弱于软引用,下一次垃圾回收到来回收
	4.虚: 通过引用无法获取对象，对象被GC回收时收到一个系统通知

	垃圾回收算法
	
	1.标记-清除 Mark Sweep  :标记清理效率不高，内存不连续
	2.复制算法 Copying      :内存分为大小相等的两块 一个，一块用完就将活着的对象移动到另一块上，将当前的清空。
	3.标记-整理Mark Compact:所有存货的对象向一端移动，然后直接清理掉边界以外的内存
	4.分代收集算法 ：新生代少量对象存活，复制算法。老生代：存活率高，标记整理，标记清理算法
	
	枚举根节点
	
	STW : Stop The World
	可达性分析期间整个执行系统看起来就想被冻结在某个时间点上，不可以出现分析过程中对象引用关系发生变化的情况。
	
	如何进入GC
	
	1.安全点

	指令复用：方法调用 循环跳转 异常跳转
	抢先式中断：不需要线程配合，GC发生时把所有的线程全部中断，如果有线程中断的地方不在安全点儿就恢复线程，让它执行到安全点。
	主动式中断：GC时不直接对线程操作，设置标志，每个线程执行时主动轮询标志，发现标志为真就主动挂起。
	
	2.安全区域
	
	安全区域：代码中引用关系不会发生变化。
	线程进入安全区域时，自己标识。线程从安全区域时离开时先检查是否完成了根节点枚举。
	
	GC收集器
	
	1.串行垃圾回收器（Serial Garbage Collector）:单个线程,Stop The World ,虚拟机运行在Client模式下默认的新生代垃圾收集器。
	2.并行垃圾回收器（Parallel Garbage Collector）:Serial GC的多线程版本 。Server模式下虚拟机中首先的新生代收集器。
	3.并发标记扫描垃圾回收器（CMS Garbage Collector）:标记清理算法，并发收集器。
	4.G1垃圾回收器（G1 Garbage Collector）
	5.Parallel Scavenge 收集器 :复制算法。吞吐量。新生代
	
	
	 类加载的时机
 	类加载虚拟机规范没有规定具体的时间
 
 	类初始化条件：有且仅有
	 1.new putstatic getstatic invokestatic (final类常量已经在编译期间把结果放入到常量池 除外)
	 
	 2.反射调用
	 
	 3.main方法启动虚拟机的类
	
	 4.初始化类其父类没有初始化初始化其父类
 
 	 5.REF_getStatic,REF_putStatic,REF_invokeStatic的方法句柄方法句柄对应的类没有初始化，则需要触发其初始化
 
 
 	类不初始化情况
 
	 1.通过子类引用父类的静态字段，不会导致类初始化
	 
	 2.数组来定义引用类不会触发此类的初始化
	 
	 3.常量在编译期间会存入调用类的常量池中，本质上没有直接引用定义常量的类因此不会触发定义常量类的初始化
	 
	 接口初始化
 
	 1.接口中不能使用static{} 但是编译器会为接口生成<clinit>() 类构造器，用于初始化接口中所定义的成员变量
	 
	 2.接口初始化和类初始化不同点：接口初始化使用到父接口的时候才会初始化父接口
 
 	类加载的过程
 	
	 1、加载
	 1）通过类的全限定名获取此类的二进制流
	 2）将这个字节流代表的静态存储结构转化为方法区运行时数据结构
	 3）在内存中生成代表这个类的java.lang.Class对象，作为方法区这个类的各种数据入口
	 2、验证
	 1）文件格式验证 魔术版本等
	 2）元数据验证    对垒的元数据进行校验，语义分析，java语言规范
	 3）字节码验证   程序语义是符合合法符合逻辑 对类的方法体进行验证分析保证被校验的方法在运行时不会危害虚拟机安全事件。
	 3、准备
	 1）类变量分配内存，设置类变量初始值，这些变量所使用的内存都将在方法区中进行分配 
	 2）非final类变量 例如：public static int value = 2; 准备阶段是0  把 value=2 的putstatic指令程序被编译后放到类构造方法 初始化阶段才会执行  <clinit>() 
	 3）final 类变量  public static final String HELLO_WORLD = "hello world" 在准备期间完成初始化
	 编译时javac会为 静态常量生成常量值：ConstantValue 准备阶段对应的字段会根据ConstantValue赋值
	 public static final java.lang.String HELLO_WORLD;
	 descriptor: Ljava/lang/String;
	 flags: ACC_PUBLIC, ACC_STATIC, ACC_FINAL
	 ConstantValue: String hello world
	 
	 4、解析
	 常量池符号引用替换成直接引用的过程
	 5、初始化
	 1）执行  <clinit>() 编译器自动收集所有类变量的赋值、static{}、收集顺序：语句在源码中出现的位置
	 2）静态代码块只能访问到定义在静态代码块之前的变量，定义在之后的变量只能赋值不能访问
	 3）<clinit>()和实例的<init>()方法不同，不用显示调用父类的 <clinit>()
	 4）父类的初始化要优先于子类的初始化父类的静态语句块执行要优先于子类
	 5）<clinit>()非必须，没有静态语句块和类变量就不会有<clinit>()
	 6）虚拟机保证<clinit>()多个线程同时刻只有一个线程执行, 类只会被初始化一次

	 类加载器
	 1)类加载器不同，类不同
	 2）双亲委派机制：
	 	Bootstrap ClassLoader <JAVA_HOME>/lib/rt.jar
	  Extension ClassLoader <JAVA_HOME>/lib/ext
	  Application ClassLoader 
	 -XX:+TraceClassLoading
		
	