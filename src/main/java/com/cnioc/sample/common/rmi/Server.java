package com.cnioc.sample.common.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 * 
 * <p>Title:Server</p>
 * <p>Desc:</p>
 * @author fangj
 * @date 2017年12月18日 下午5:12:55
 */
public class Server extends UnicastRemoteObject implements Hello {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4129719956304965236L;

	/**
	 * 
	 * @throws RemoteException
	 */
	protected Server() throws RemoteException {
		super();
	}

	@Override
	public String sayHello() throws RemoteException {
		return "Hello, world!";
	}
	
	public static void main(String[] args) {
		 try {
			 	//创建远程对象
	            Server obj = new Server();
	            //暴露远程对象	
	            Naming.rebind("hello", obj);  
	            System.err.println("Server ready");
	        } catch (Exception e) {
	            System.err.println("Server exception: " + e.toString());
	            e.printStackTrace();
	        }
	}
}
