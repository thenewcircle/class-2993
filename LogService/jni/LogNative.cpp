#include <jni.h>
#include <android/log.h>

namespace com_marakana_android_lognative {

void logN( JNIEnv *env, jclass clazz, jint priority, jstring tag, jstring msg) {

	// Convert jstring to char*
	const char *c_tag = env->GetStringUTFChars(tag, 0);
	const char *c_msg = env->GetStringUTFChars(msg, 0);

	__android_log_write(priority, c_tag, c_msg);

	// Release char*
	env->ReleaseStringUTFChars(tag, c_tag);
	env->ReleaseStringUTFChars(msg, c_msg);
}

// Create the method table
static JNINativeMethod method_table[] = {
		{ "logN", "(ILjava/lang/String;Ljava/lang/String;)V", (void *) logN }
};
}

using namespace com_marakana_android_lognative;

/* Called at System.loadLibrary() time */
extern "C" jint JNI_OnLoad(JavaVM* vm, void* reserved) {
	JNIEnv* env;
	if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
		return JNI_ERR;
	} else {
		jclass clazz = env->FindClass("com/marakana/android/logservice/LogLib");
		if (clazz) {
			jint ret = env->RegisterNatives(clazz, method_table,
					sizeof(method_table) / sizeof(method_table[0]));
			env->DeleteLocalRef(clazz);
			return ret == 0 ? JNI_VERSION_1_6 : JNI_ERR;
		} else {
			return JNI_ERR;
		}
	}
}
