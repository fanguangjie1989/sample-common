package com.cnioc.sample.common.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * <p>
 * Title:Client
 * </p>
 * <p>
 * Desc:Block IO Client 阻塞IO客户端
 * 同步阻塞
 * </p>
 * 
 * @author fangj
 * @date 2017年12月20日 上午9:07:07
 */
public class Client {
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 8765;

	public static void main(String[] args) {
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			socket = new Socket(HOST, PORT);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 向服务端发送数据
			out.println(" hello server ");
			System.out.println("server said :" + in.readLine());

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
