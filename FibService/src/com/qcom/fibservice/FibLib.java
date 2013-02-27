package com.qcom.fibservice;

public class FibLib {
 
	/** Java recursive Fibonacci */
	public static long fibJ(long n) {
		if(n==0) return 0;
		if(n==1) return 1;
		return fibJ(n-1)+fibJ(n-2);
	}
	
	static {
		System.loadLibrary("FibNative");
	}
	/** Native recursive Fibonacci */
	public static native long fibN(long n);
}
