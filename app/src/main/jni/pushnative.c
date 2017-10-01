#include <jni.h>
#include <android/log.h>
#include "x264.h"

#define LOG_TAG "WH_LIVE"
#define LOGI(FORMAT, ...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, FORMAT, ##__VA_ARGS__)

JNIEXPORT void JNICALL
Java_wh_whlive_jni_PushNative_startPush(JNIEnv *env, jobject instance, jstring url_) {
    const char *url = (*env)->GetStringUTFChars(env, url_, 0);

    // TODO

    (*env)->ReleaseStringUTFChars(env, url_, url);
}

JNIEXPORT void JNICALL
Java_wh_whlive_jni_PushNative_stopPush(JNIEnv *env, jobject instance) {

    // TODO

}

JNIEXPORT void JNICALL
Java_wh_whlive_jni_PushNative_release(JNIEnv *env, jobject instance) {

    // TODO

}

/**
 * 设置视频参数
 */
JNIEXPORT void JNICALL
Java_wh_whlive_jni_PushNative_setVideoOptions(JNIEnv *env, jobject instance, jint width,
                                              jint height, jint bitRat, jint fps) {

    /********************************************************************
     * 参照x264源码的example执行流程
     * 1. x264_param_default_preset                 设置
     * 2. x264_param_apply_profile                  设置档次
     * 3. x264_picture_alloc                        输入图像初始化
     * 4. x264_encoder_open                         打开编码器
     * 5. x264_encoder_encode                       编码
     * 6. x264_encoder_close                        关闭编码器，释放资源
     ********************************************************************/

    x264_param_t param;
    /**
     * 查看相关源码设置参数
     * ultrafast 编码速度超快
     * zerolatency 零延迟，无B帧
     */
    x264_param_default_preset(&param, "ultrafast", "zerolatency");

    /*
     * CSP of encoded bitstream
     * 编码输入的像素格式YUV420P
     */
    param.i_csp = X264_CSP_I420;
    param.i_width = width;
    param.i_height = height;
    /*
     * 参数i_rc_method表示码率控制，CQP(恒定质量)，CRF(恒定码率)，ABR(平均码率)
     * 恒定码率，会尽量控制在固定码率
      */
    param.rc.i_rc_method = X264_RC_CRF;
    /*
     * 码率(比特率,单位Kbps)
     */
    param.rc.i_bitrate = bitRat / 1000;
    /*
     * 瞬时最大码率
     */
    param.rc.i_vbv_max_bitrate = bitRat / 1000 * 1.2;
    /*
     * VFR input.  If 1, use timebase and timestamps for ratecontrol purposes.
     * If 0, use fps only.
     * 通过注释可知0代表码率控制不通过timebase和timestamp，而是fps
     */
    param.b_vfr_input = 0;
    /*
     * 帧率分子
     */
    param.i_fps_num = fps;
    /*
     * 帧率分母
     */
    param.i_fps_den = 1;
    /*
     * 由于b_vfr_input设置为0，所以timebas的分子分母与fps的相同
     */
    param.i_timebase_den = param.i_fps_num;
    param.i_timebase_num = param.i_fps_den;
    /*
     * 并行编码线程数量，0默认为多线程
     */
    param.i_threads = 1;
    /*
     * put SPS/PPS before each keyframe
     * 是否把SPS和PPS放入每一个关键帧
     * SPS Sequence Parameter Set 序列参数集
     * PPS Picture Parameter Set 图像参数集
     * 为了提高图像的纠错能力
     */
    param.b_repeat_headers = 1;
    /*
     * 设置Level级别
     */
    param.i_level_idc = 51;

    /**
     * 设置Profile档次
     * baseline级别，没有B帧
     */
    x264_param_apply_profile(&param, "baseline");

    /**
     * x264_picture_t（输入图像）初始化
     */
    x264_picture_t pic_in;
    x264_picture_alloc(&pic_in, param.i_csp, param.i_width, param.i_height);

    /**
     * 打开编码器
     */
    x264_t *x264_encoder = x264_encoder_open(&param);
    if (x264_encoder) {
        LOGI("打开编码器成功...");
    }
}

JNIEXPORT void JNICALL
Java_wh_whlive_jni_PushNative_setAudioOptions(JNIEnv *env, jobject instance, jint sampleRatInHz,
                                              jint channel) {

    // TODO

}

JNIEXPORT void JNICALL
Java_wh_whlive_jni_PushNative_fireVideo(JNIEnv *env, jobject instance, jbyteArray data_) {
    jbyte *data = (*env)->GetByteArrayElements(env, data_, NULL);

    // 视频数据转化为YUV420P
    // TODO

    (*env)->ReleaseByteArrayElements(env, data_, data, 0);
}

JNIEXPORT void JNICALL
Java_wh_whlive_jni_PushNative_fireAudio(JNIEnv *env, jobject instance, jbyteArray data_, jint len) {
    jbyte *data = (*env)->GetByteArrayElements(env, data_, NULL);

    // TODO

    (*env)->ReleaseByteArrayElements(env, data_, data, 0);
}