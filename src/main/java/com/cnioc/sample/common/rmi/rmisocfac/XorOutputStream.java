package com.cnioc.sample.common.rmi.rmisocfac;

import java.io.*;
/**
 * 
 * <p>Title:XorOutputStream</p>
 * <p>Desc:</p>
 * @author fangj
 * @date 2017年12月18日 下午5:06:24
 */
public class XorOutputStream extends FilterOutputStream {

	/**
	 * The byte used to "encrypt" each byte of data.
	 */
	private final byte pattern;

	/**
	 * Constructs an output stream that uses the specified pattern to "encrypt"
	 * each byte of data.
	 */
	public XorOutputStream(OutputStream out, byte pattern) {
		super(out);
		this.pattern = pattern;
	}

	/**
	 * XOR's the byte being written with the pattern and writes the result.
	 * @param b
	 */
	@Override
	public void write(int b) throws IOException {
		out.write((b ^ pattern) & 0xFF);
	}
}
