package com.cnioc.sample.common.rmi.rmisocfac;

import java.rmi.registry.*;
/**
 * 
 * <p>Title:HelloClient</p>
 * <p>Desc:</p>
 * @author fangj
 * @date 2017年12月18日 下午5:13:27
 */
public class HelloClient {

	public static void main(String args[]) {

		/*
		 * Create and install a security manager
		 */
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
			Hello obj = (Hello) registry.lookup("Hello");
			String message = obj.sayHello();
			System.out.println(message);

		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
