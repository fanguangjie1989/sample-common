package com.cnioc.sample.common.rmi.rmisocfac;

import java.io.*;
import java.net.*;
/**
 * 
 * <p>Title:XorServerSocket</p>
 * <p>Desc:</p>
 * @author fangj
 * @date 2017年12月18日 下午5:06:56
 */
public class XorServerSocket extends ServerSocket {
  
    /**
     * The pattern used to "encrypt" and "decrypt" each byte sent
     * or received by the socket.
     */
    private final byte pattern;
  
    /** 
     * Constructor for class XorServerSocket.
     */
    public XorServerSocket(int port, byte pattern) throws IOException {
        super(port);
        this.pattern = pattern;
    }
  
    /** 
     * Creates a socket of type XorSocket and then calls 
     * implAccept to wait for a client connection.
     */
    @Override
    public Socket accept() throws IOException {
        Socket s = new XorSocket(pattern);
        implAccept(s);
        return s;
    }
}




