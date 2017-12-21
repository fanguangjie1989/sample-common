package com.cnioc.sample.common.socket.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
 * <p>Title:ServerCompletionHandler</p>
 * <p>Desc:</p>
 * @author fangj
 * @date 2017年12月20日 下午2:42:47
 */
public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Server> {
	
	private static final AtomicInteger HANDLER_NUM = new AtomicInteger(1);
	
	public ServerCompletionHandler() {
		System.out.println("HANDLER"+HANDLER_NUM.getAndIncrement());
	}

	@Override
	public void completed(AsynchronousSocketChannel asc, Server attachment) {
		//当有下一个客户端接入的时候 直接调用Server的accept方法，这样反复执行下去，保证多个客户端都可以阻塞
		System.out.println(Thread.currentThread().getName()+" invoked read()");
		attachment.assc.accept(attachment, this);
		read(asc);
	}

	private void read(final AsynchronousSocketChannel asc) {
		//读取数据
		ByteBuffer buf = ByteBuffer.allocate(1024);
		asc.read(buf, buf, new CompletionHandler<Integer, ByteBuffer>() {
			@Override
			public void completed(Integer resultSize, ByteBuffer attachment) {
				//进行读取之后,重置标识位
				attachment.flip();
				//获得读取的字节数
				System.out.println(Thread.currentThread().getName()+" Server -> " + "收到客户端的数据长度为:" + resultSize);
				//获取读取的数据
				String resultData = new String(attachment.array()).trim();
				System.out.println(Thread.currentThread().getName()+" Server -> " + "收到客户端的数据信息为:" + resultData);
				String response = "服务器响应, 收到了客户端发来的数据: " + resultData;
				write(asc, response);
			}
			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				exc.printStackTrace();
			}
		});
	}
	
	private void write(AsynchronousSocketChannel asc, String response) {
		try {
			ByteBuffer buf = ByteBuffer.allocate(1024);
			buf.put(response.getBytes());
			buf.flip();
			asc.write(buf).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void failed(Throwable exc, Server attachment) {
		exc.printStackTrace();
	}

}
