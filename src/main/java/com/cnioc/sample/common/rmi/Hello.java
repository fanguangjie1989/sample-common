package com.cnioc.sample.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 
 * <p>Title:Hello</p>
 * <p>Desc:</p>
 * @author fangj
 * @date 2017年12月18日 下午5:12:41
 */
public interface Hello extends Remote {
	/**
	 * say hello 
	 * @return
	 * @throws RemoteException
	 */
	String sayHello() throws RemoteException;
}
