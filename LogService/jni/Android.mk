LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_LDLIBS += -llog
LOCAL_MODULE    := LogNative
LOCAL_SRC_FILES := LogNative.cpp

include $(BUILD_SHARED_LIBRARY)
