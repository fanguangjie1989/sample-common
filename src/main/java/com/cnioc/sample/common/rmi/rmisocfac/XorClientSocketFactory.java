package com.cnioc.sample.common.rmi.rmisocfac;

import java.io.*;
import java.net.*;
import java.rmi.server.*;
/**
 * 
 * <p>Title:XorClientSocketFactory</p>
 * <p>Desc:</p>
 * @author fangj
 * @date 2017年12月18日 下午4:58:51
 */
public class XorClientSocketFactory implements RMIClientSocketFactory, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -663835670875300903L;
	/**
	 * pattern
	 */
	private final byte pattern;
	
	public XorClientSocketFactory(byte pattern) {
		this.pattern = pattern;
	}
	/**
	 * create a socket
	 * @param host 主机名称
	 * @param 端口号
	 */
	@Override
	public Socket createSocket(String host, int port) throws IOException {
		return new XorSocket(host, port, pattern);
	}
	@Override
	public int hashCode() {
		return (int) pattern;
	}
	@Override
	public boolean equals(Object obj) {
		return (getClass() == obj.getClass() && pattern == ((XorClientSocketFactory) obj).pattern);
	}

}
