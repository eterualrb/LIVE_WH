package wh.whlive.jni;

public class PushNative {

    static {
        System.loadLibrary("wh-live");
    }

    /**
     * 开始推流
     *
     * @param url
     */
    public native void startPush(String url);

    /**
     * 停止推流
     */
    public native void stopPush();

    /**
     * 释放资源
     */
    public native void release();

    /**
     * 设置视频参数
     *
     * @param width
     * @param height
     * @param bitRat
     * @param fps
     */
    public native void setVideoOptions(int width, int height, int bitRat, int fps);

    /**
     * 设置音频参数
     *
     * @param sampleRatInHz
     * @param channel
     */
    public native void setAudioOptions(int sampleRatInHz, int channel);

    /**
     * 发送视频数据到native层处理
     *
     * @param data
     */
    public native void fireVideo(byte[] data);

    /**
     * 发送音频数据到native层处理
     *
     * @param data
     * @param len
     */
    public native void fireAudio(byte[] data, int len);

}
