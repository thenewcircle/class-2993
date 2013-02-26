#include <jni.h>
#include <android/log.h>

namespace com_marakana_android_lognative {

void logN( JNIEnv *env, jclass clazz, jint priority, jstring tag, jstring msg) {

	// Convert jstring to char*

	// Use __android_log_write(int prio, const char *tag, const char *text)

	// Release char*
}

// Create the method table

}

// Copy/update JNI_OnLoad()
