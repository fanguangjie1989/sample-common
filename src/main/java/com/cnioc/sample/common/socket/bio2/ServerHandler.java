package com.cnioc.sample.common.socket.bio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * <p>
 * Title:ServerHandler
 * </p>
 * <p>
 * Desc:socket handler socket处理器
 * </p>
 * 
 * @author fangj
 * @date 2017年12月20日 上午9:14:21
 */
public class ServerHandler implements Runnable {

	private Socket socket;
	
	private final long id;
	
	public ServerHandler(Socket socket,long id) {
		this.socket = socket;
		this.id =  id;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(),true);
			String body = null;
			while (true) {
				body = in.readLine();
				if (body == null){
					break;
				}
				System.out.println(Thread.currentThread().getName()+" client <"+id+"> said :" + body);
				out.println("服务器端回送响的应数据.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			socket = null;
		}
	}

}
