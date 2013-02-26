/*
 * LogNative.c
 *
 *  Created on: Feb 26, 2013
 *      Author: marko
 */
#include "com_marakana_android_lognative_LogLib.h"
#define NULL ((void *) 0)

JNIEXPORT void JNICALL Java_com_marakana_android_lognative_LogLib_logN
(JNIEnv *env, jclass clazz, jint priority, jstring tag, jstring msg) {

	// Convert jstring to char*
	const char *c_tag = (*env)->GetStringUTFChars(env, tag, NULL);
	const char *c_msg = (*env)->GetStringUTFChars(env, msg, NULL);

	__android_log_write(priority, c_tag, c_msg);

	// Release char*
	(*env)->ReleaseStringUTFChars(env, tag, c_tag);
	(*env)->ReleaseStringUTFChars(env, msg, c_msg);

}

