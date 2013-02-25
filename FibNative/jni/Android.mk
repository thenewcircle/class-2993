LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := FibNative
LOCAL_SRC_FILES := FibNative.c

include $(BUILD_SHARED_LIBRARY)
