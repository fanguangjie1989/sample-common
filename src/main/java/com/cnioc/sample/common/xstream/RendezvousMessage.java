package com.cnioc.sample.common.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * <p>Title:RendezvousMessage</p>
 * <p>Desc:</p>
 * @author fangj
 * @date 2017年12月18日 下午5:13:53
 */
@XStreamAlias("message")
public class RendezvousMessage {
	@XStreamAlias("type")
	private int messageType;

	public RendezvousMessage(int messageType) {
		this.messageType = messageType;
	}
}
