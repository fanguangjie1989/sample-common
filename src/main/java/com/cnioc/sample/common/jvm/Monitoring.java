package com.cnioc.sample.common.jvm;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * <p>Title:Monitoring</p>
 * <p>Desc:
 * VM args: -Xms100m -Xmx100m -XX:+UseSerialGC
 * </p>
 * @author fangj
 * @date 2017年12月26日 上午10:01:58
 */
public class Monitoring {
	static class OOMObject{
		int[] array = new int[64*1024];
	}
	public static void fillHeap(int num) throws InterruptedException{
		List<OOMObject> list = new ArrayList<OOMObject>();
		for(int i =0 ;i<num ;i++){
			Thread.sleep(500);
			list.add(new OOMObject());
		}
		System.gc();
	}
	public static void main(String[] args) throws InterruptedException {
		fillHeap(1000);
	}
}
