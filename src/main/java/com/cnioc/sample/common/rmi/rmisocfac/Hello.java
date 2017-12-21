package com.cnioc.sample.common.rmi.rmisocfac;
/**
 * 
 * <p>Title:Hello</p>
 * <p>Desc:rmi remote method invoke</p>
 * <p></>
 * @author fangj
 * @date 2017年12月18日 下午4:52:48
 */
public interface Hello extends java.rmi.Remote {
	/**
	 * say hello
	 * @return
	 * @throws java.rmi.RemoteException
	 */
    String sayHello() throws java.rmi.RemoteException;
}

    
