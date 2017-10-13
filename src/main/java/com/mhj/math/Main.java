package com.mhj.math;

public class Main {

	public static void main(String[] args) {
		System.out.println(leftPadZeros(3, 1));

	}

	private static String leftPadZeros(final int number, final long value) {
		return String.format("%0"+ number +"d", value);	
	}

}
