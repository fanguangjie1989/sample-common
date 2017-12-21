package com.cnioc.sample.common.rmi.rmisocfac;

import java.rmi.server.*;
import java.rmi.registry.*;
/**
 * 
 * <p>Title:HelloImpl</p>
 * <p>Desc:Hello的实现类</p>
 * @author fangj
 * @see Hello
 * @date 2017年12月18日 下午4:53:32
 */
public class HelloImpl implements Hello {

	/**
	 * Constructs a HelloImpl remote object.
	 */
	public HelloImpl() {
	}

	/**
	 * Returns the string "Hello World!".
	 */
	@Override
	public String sayHello() {
		return "Hello World!";
	}
	
	/**
	 * just use main method to write a simple test
	 * @param args
	 */
	public static void main(String args[]) {

		/*
		 * Create and install a security manager
		 */
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		byte pattern = (byte) 0xAC;
		try {
			/*
			 * Create remote object and export it to use custom socket
			 * factories.
			 */
			HelloImpl obj = new HelloImpl();
			RMIClientSocketFactory csf = new XorClientSocketFactory(pattern);
			RMIServerSocketFactory ssf = new XorServerSocketFactory(pattern);
			Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0, csf, ssf);

			/*
			 * Create a registry and bind stub in registry.
			 */
			LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
			registry.rebind("Hello", stub);
			System.out.println("HelloImpl bound in registry");

		} catch (Exception e) {
			System.out.println("HelloImpl exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
