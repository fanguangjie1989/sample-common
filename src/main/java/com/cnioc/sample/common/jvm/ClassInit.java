package com.cnioc.sample.common.jvm;
/**
 * 
 * <p>Title:ClassInit</p>
 * <p>Desc:
 * 类加载的时机<br/>
 * 类加载虚拟机规范没有规定具体的时间<br/>
 * 类初始化条件：有且仅有<br/>
 * 1.new putstatic getstatic invokestatic (final类常量已经在编译期间把结果放入到常量池 除外)
 * <br/>
 * 2.反射调用
 * <br/>
 * 3.main方法启动虚拟机的类
 * <br
 * 4.初始化类其父类没有初始化初始化其父类
 * <br/>
 * 5.REF_getStatic,REF_putStatic,REF_invokeStatic的方法句柄方法句柄对应的类没有初始化，则需要触发其初始化
 * <br/>
 * 类不初始化情况
 * <br/>
 * 1.通过子类引用父类的静态字段，不会导致类初始化
 * <br/>
 * 2.数组来定义引用类不会触发此类的初始化
 * <br/>
 * 3.常量在编译期间会存入调用类的常量池中，本质上没有直接引用定义常量的类因此不会触发定义常量类的初始化
 * <br/>
 * 接口初始化
 * <br/>
 * 1.接口中不能使用static{} 但是编译器会为接口生成<clinit>() 类构造器，用于初始化接口中所定义的成员变量
 * <br/>
 * 2.接口初始化和类初始化不同点：接口初始化使用到父接口的时候才会初始化父接口
 * <br/>
 * 类加载的过程<br/>
 * 1、加载<br/>
 * 1）通过类的全限定名获取此类的二进制流
 * 2）将这个字节流代表的静态存储结构转化为方法区运行时数据结构
 * 3）在内存中生成代表这个类的java.lang.Class对象，作为方法区这个类的各种数据入口
 * 2、验证<br/>
 * 1）文件格式验证 魔术版本等
 * 2）元数据验证    对垒的元数据进行校验，语义分析，java语言规范
 * 3）字节码验证   程序语义是符合合法符合逻辑 对类的方法体进行验证分析保证被校验的方法在运行时不会危害虚拟机安全事件。
 * 3、准备<br/>
 * 1）类变量分配内存，设置类变量初始值，这些变量所使用的内存都将在方法区中进行分配 
 * 2）非final类变量 例如：public static int value = 2; 准备阶段是0  把 value=2 的putstatic指令程序被编译后放到类构造方法 初始化阶段才会执行  <clinit>() 
 * 3）final 类变量  public static final String HELLO_WORLD = "hello world" 在准备期间完成初始化
 * 编译时javac会为 静态常量生成常量值：ConstantValue 准备阶段对应的字段会根据ConstantValue赋值
 * public static final java.lang.String HELLO_WORLD;
 * descriptor: Ljava/lang/String;
 * flags: ACC_PUBLIC, ACC_STATIC, ACC_FINAL
 * ConstantValue: String hello world
 * 
 * 4、解析<br/>
 * 常量池符号引用替换成直接引用的过程
 * 5、初始化<br/>
 * 1）执行  <clinit>() 编译器自动收集所有类变量的赋值、static{}、收集顺序：语句在源码中出现的位置<br/>
 * 2）静态代码块只能访问到定义在静态代码块之前的变量，定义在之后的变量只能赋值不能访问<br/>
 * 3）<clinit>()和实例的<init>()方法不同，不用显示调用父类的 <clinit>()<br/>
 * 4）父类的初始化要优先于子类的初始化父类的静态语句块执行要优先于子类<br/>
 * 5）<clinit>()非必须，没有静态语句块和类变量就不会有<clinit>()<br/>
 * 6）虚拟机保证<clinit>()多个线程同时刻只有一个线程执行, 类只会被初始化一次<br/>
 *
 * 类加载器<br/>
 * 1)类加载器不同，类不同
 * 2）双亲委派机制：
 * 	Bootstrap ClassLoader <JAVA_HOME>/lib/rt.jar
 *  Extension ClassLoader <JAVA_HOME>/lib/ext
 *  Application ClassLoader 
 * -XX:+TraceClassLoading
 * </p>
 * @author fangj
 * @date 2017年12月26日 下午3:10:55
 */
public class ClassInit {
	static {
		System.out.println("ClassInit init");
	}
	public static void main(String[] args) {
		//SupperClass init
		System.out.println(SubClass.value);
		//常量在编译期间会存入调用类的常量池中，本质上没有直接引用定义常量的类因此不会触发定义常量类的初始化
		System.out.println(SubClass.HELLO_WORLD);
		//不会触发类的初始化 触发 [Lcom.cnioc.sample.common.jvm.SubClass 初始化   该类由虚拟机自动生成 直接继承 java.lang.Object 创建动作由字节码指令 newArray
		//SubClass[] subs = new SubClass[2];
	}
}
