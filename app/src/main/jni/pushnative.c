#include <jni.h>

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

JNIEXPORT void JNICALL
Java_wh_whlive_jni_PushNative_setVideoOptions(JNIEnv *env, jobject instance, jint width,
                                              jint height, jint bitRat, jint fps) {

    // TODO

}

JNIEXPORT void JNICALL
Java_wh_whlive_jni_PushNative_setAudioOptions(JNIEnv *env, jobject instance, jint sampleRatInHz,
                                              jint channel) {

    // TODO

}

JNIEXPORT void JNICALL
Java_wh_whlive_jni_PushNative_fireVideo(JNIEnv *env, jobject instance, jbyteArray data_) {
    jbyte *data = (*env)->GetByteArrayElements(env, data_, NULL);

    // TODO

    (*env)->ReleaseByteArrayElements(env, data_, data, 0);
}

JNIEXPORT void JNICALL
Java_wh_whlive_jni_PushNative_fireAudio(JNIEnv *env, jobject instance, jbyteArray data_, jint len) {
    jbyte *data = (*env)->GetByteArrayElements(env, data_, NULL);

    // TODO

    (*env)->ReleaseByteArrayElements(env, data_, data, 0);
}