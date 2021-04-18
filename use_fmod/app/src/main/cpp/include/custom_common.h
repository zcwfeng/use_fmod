//
// Created by 张传伟 on 2021/4/17.
//
#include <jni.h>
#include <fmod/fmod.hpp>
#include <string>
#include <android/log.h>

#ifndef USE_FMOD_CUSTOM_COMMON_H
#define USE_FMOD_CUSTOM_COMMON_H
/* Header for class USE_FMOD_CUSTOM_COMMONy */
#ifdef __cplusplus
extern "C" {
#endif
#undef USE_FMOD_CUSTOM_COMMON_MODE_NORMAL
#define USE_FMOD_CUSTOM_COMMON_MODE_NORMAL 0L
#undef USE_FMOD_CUSTOM_COMMON_MODE_LUOLI
#define USE_FMOD_CUSTOM_COMMON_MODE_LUOLI 1L
#undef USE_FMOD_CUSTOM_COMMON_MODE_DASHU
#define USE_FMOD_CUSTOM_COMMON_MODE_DASHU 2L
#undef USE_FMOD_CUSTOM_COMMON_MODE_JINGSONG
#define USE_FMOD_CUSTOM_COMMON_MODE_JINGSONG 3L
#undef USE_FMOD_CUSTOM_COMMON_MODE_GAOGUAI
#define USE_FMOD_CUSTOM_COMMON_MODE_GAOGUAI 4L
#undef USE_FMOD_CUSTOM_COMMON_MODE_KONGLING
#define USE_FMOD_CUSTOM_COMMON_MODE_KONGLING 5L


#define LOG_TAG "native_zcw"

#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_WARN,LOG_TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

extern "C"
JNIEXPORT void JNICALL
Java_top_zcwfeng_fmod_FmodChangeVoice_voiceChangeNative(JNIEnv *env, jobject thiz, jint mode,
                                                        jstring path);

#ifdef __cplusplus
}
#endif
#endif //USE_FMOD_CUSTOM_COMMON_H
