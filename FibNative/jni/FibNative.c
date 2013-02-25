/*
 * FibNative.c
 *
 *  Created on: Feb 25, 2013
 *      Author: marko
 */

#include "com_qcom_fibnative_FibLib.h"

jlong fib(jlong n) {
	if(n==0) return 0;
	if(n==1) return 1;
	return fib(n-1)+fib(n-2);
}

JNIEXPORT jlong JNICALL Java_com_qcom_fibnative_FibLib_fibN
  (JNIEnv *env, jclass clazz, jlong n) {
	return fib(n);
}
