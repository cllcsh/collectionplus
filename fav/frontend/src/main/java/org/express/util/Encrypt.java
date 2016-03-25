package org.express.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称加密
 * @author Rei Ayanami
 *
 */
public class Encrypt {

	private static final String KEY="4c5e8d9a";
	private static final String EncodeAlgorithm = "DES";
	private static Encrypt instance = null;

	public static Encrypt getInstance() {
		if (instance == null) {
			instance = new Encrypt();
			if (!instance.init()) {
				instance = null;
			}
		}
		return instance;
	}

	private SecretKey key = null;

	private boolean init() {
		try {
			key = new SecretKeySpec(KEY.getBytes(), EncodeAlgorithm);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key != null;
	}

	private Cipher getCipher(int mode) {
		try {
			Cipher cipher = Cipher.getInstance(EncodeAlgorithm);
			cipher.init(mode, key);
			return cipher;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Cipher getEncodeCipher() {
		return this.getCipher(Cipher.ENCRYPT_MODE);
	}

	public Cipher getDecodeCipher() {
		return this.getCipher(Cipher.DECRYPT_MODE);
	}

	/**
	 * 解密字符串
	 * @param str
	 * @return
	 */
	public String decode(String str) {
		if(str==null)return null;
		Cipher cipher = getDecodeCipher();
		StringBuffer sb = new StringBuffer();
		int blockSize = cipher.getBlockSize();
		int outputSize = cipher.getOutputSize(blockSize);
		byte[] src = stringToBytes(str);
		byte[] outBytes = new byte[outputSize];
		int i = 0;
		try {
			for (; i <= src.length - blockSize; i = i + blockSize) {
				int outLength = cipher.update(src, i, blockSize, outBytes);
				sb.append(new String(outBytes, 0,outLength));
			}
			if (i == src.length)
				outBytes = cipher.doFinal();
			else {
				outBytes = cipher.doFinal(src, i, src.length - i);
			}
			sb.append(new String(outBytes));
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密字符串
	 * @param str
	 * @return
	 */
	public String encode(String str) {
		if(str==null)return null;
		Cipher cipher = getEncodeCipher();
		StringBuffer sb = new StringBuffer();
		int blockSize = cipher.getBlockSize();
		int outputSize = cipher.getOutputSize(blockSize);
		byte[] src = str.getBytes();
		byte[] outBytes = new byte[outputSize];
		int i = 0;
		try {
			for (; i <= src.length - blockSize; i = i + blockSize) {
				int outLength = cipher.update(src, i, blockSize, outBytes);
				sb.append(bytesToString(outBytes, outLength));
			}
			if (i == src.length)
				outBytes = cipher.doFinal();
			else {
				outBytes = cipher.doFinal(src, i, src.length - i);
			}
			sb.append(bytesToString(outBytes));
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String bytesToString(byte[] bs) {
		if (bs == null || bs.length == 0)
			return "";
		return bytesToString(bs, bs.length);
	}

	private String bytesToString(byte[] bs, int len) {
		if (bs == null || bs.length == 0)
			return "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			sb.append(String.format("%02X", bs[i]));
		}
		return sb.toString();
	}

	private byte[] stringToBytes(String str) {
		if (str == null || str.length() < 2 || str.length() % 2 != 0)
			return new byte[0];
		int len = str.length();
		byte[] bs = new byte[len / 2];
		for (int i = 0; i * 2 < len; i++) {
			bs[i] = (byte) (Integer.parseInt(str.substring(i * 2, i * 2 + 2),
					16) & 0xFF);
		}
		return bs;
	}
	
	public static void main(String[] a)
	{
		String aString = "123456";
		Encrypt encrypt = Encrypt.getInstance();
		System.out.print(encrypt.encode(aString));
	}
}

