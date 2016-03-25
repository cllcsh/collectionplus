package com.osource.base.common;



public class ColorGenerator {
	public static int randomNumber() {
		return (int) Math.floor(Math.random() * 256);
	}

	public static String decToHex(int dec) {
		String hexStr = "0123456789ABCDEF";
		int low = Math.abs(dec % 16);
		int high = Math.abs((dec - low) / 16);
		String hex = ""
				+ (hexStr.length() > high ? hexStr.charAt(high) : hexStr
						.charAt(0))
				+ (hexStr.length() > low ? hexStr.charAt(low) : hexStr
						.charAt(10));
		return hex;
	}

	public static String randomBgColor() {
		String r, g, b;
		r = decToHex(randomNumber() - 1);
		g = decToHex(randomNumber() - 1);
		b = decToHex(randomNumber() - 1);
		return "#" + r + g + b;
	}


}
