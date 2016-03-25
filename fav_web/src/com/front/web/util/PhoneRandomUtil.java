package com.front.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhoneRandomUtil {
	private static List<String> list = new ArrayList<String>();
	static{
		String[] letters = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
				, "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		for (String str : letters) {
			list.add(str);
			list.add(str.toUpperCase());
		}
		for (int i = 0; i < 10; i++) {
			list.add(String.valueOf(i));
		}
	}
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getRandom(8));
		}
	}
	
	public static String getRandom(int size){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < size; i++) {
			buffer.append(list.get(new Random().nextInt(list.size())));
		}
		return buffer.toString();
	}
}
