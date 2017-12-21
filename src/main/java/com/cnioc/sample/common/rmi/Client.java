package com.cnioc.sample.common.rmi;

import java.rmi.Naming;
/**
 * 
 * <p>Title:Client</p>
 * <p>Desc:use main method write a simple test</p>
 * @author fangj
 * @date 2017年12月18日 下午5:10:40
 */
public class Client {
	public static void main(String[] args) {
	        try {
	        	Hello stub = (Hello)Naming.lookup("hello"); 
	        	System.out.println("type of stub "+stub.getClass());
	            String response = stub.sayHello();
	            System.out.println("response: " + response);
	        } catch (Exception e) {
	            System.err.println("Client exception: " + e.toString());
	            e.printStackTrace();
	        }
	}
}
