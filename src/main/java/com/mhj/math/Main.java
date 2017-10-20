package com.mhj.math;

public class Main {

	public static void main(String[] args) {
		String str = "12345678901234567890";
		System.out.println(str.substring(0, 16));
		//System.out.println(leftPadZeros(3, 1));

	}

	public static String leftPadZeros(final int number, final long value) {
		return String.format("%0"+ number +"d", value);	
	}

}
