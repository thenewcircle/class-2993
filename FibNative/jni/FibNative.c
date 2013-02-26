/*
 * FibNative.c
 *
 *  Created on: Feb 25, 2013
 *      Author: marko
 */

#include "com_qcom_fibnative_FibLib.h"

/* Plain old C */
long fib(long n) {
	if(n==0) return 0;
	if(n==1) return 1;
	return fib(n-1)+fib(n-2);
}

/* JNI Wrapper */
JNIEXPORT jlong JNICALL Java_com_qcom_fibnative_FibLib_fibN
  (JNIEnv *env, jclass clazz, jlong n) {
	return (jlong)fib(n);
}
