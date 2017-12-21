package com.cnioc.sample.common.rmi.rmisocfac;

import java.io.*;
import java.net.*;
import java.rmi.server.*;
/**
 * 
 * <p>Title:XorServerSocketFactory</p>
 * <p>Desc:</p>
 * @author fangj
 * @date 2017年12月18日 下午5:07:56
 */
public class XorServerSocketFactory implements RMIServerSocketFactory {
	/**
	 * pattern
	 */
	private final byte pattern;
	/**
	 * 
	 * @param pattern
	 */
	public XorServerSocketFactory(byte pattern) {
		this.pattern = pattern;
	}
	/**
	 * create server socket 
	 * @param port
	 */
	@Override
	public ServerSocket createServerSocket(int port) throws IOException {
		return new XorServerSocket(port, pattern);
	}
	@Override
	public int hashCode() {
		return (int) pattern;
	}
	@Override
	public boolean equals(Object obj) {
		return (getClass() == obj.getClass() && pattern == ((XorServerSocketFactory) obj).pattern);
	}

}
