package wh.whlive.pusher;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import wh.whlive.jni.PushNative;
import wh.whlive.params.AudioParams;
import wh.whlive.utils.LogUtil;

public class AudioPusher extends Pusher {

    private AudioParams mAudioParams;
    private PushNative mPushNative;

    private AudioRecord mAudioRecord;
    private int mMinBufferSize;

    private boolean isPushing = false;

    public AudioPusher(AudioParams params, PushNative pushNative) {
        mAudioParams = params;
        mPushNative = pushNative;

        int sampleRateInHz = mAudioParams.getSampleRateInHz();
        int channelConfig = mAudioParams.getChannel() == 1 ?
                AudioFormat.CHANNEL_IN_MONO : AudioFormat.CHANNEL_IN_STEREO;
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        mMinBufferSize = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);
        mAudioRecord = new AudioRecord(
                MediaRecorder.AudioSource.MIC, // mic
                sampleRateInHz, // 采样率
                channelConfig, // 声道布局配置
                audioFormat, // 音频格式
                mMinBufferSize // 缓冲区大小
        );
    }

    @Override
    public void startPush() {
        isPushing = true;
        // 启动一个录音子线程
        new Thread(new AudioRecordTask()).start();
    }

    @Override
    public void stopPush() {
        mAudioRecord.stop();
        isPushing = false;
    }

    @Override
    public void release() {
        if (null != mAudioRecord) {
            mAudioRecord.release();
            mAudioRecord = null;
        }
    }

    class AudioRecordTask implements Runnable {

        @Override
        public void run() {
            // 开始录音
            mAudioRecord.startRecording();

            while (isPushing) {
                // 通过AndioRecoder不断读取音频数据
                byte[] buffer = new byte[mMinBufferSize];
                int length = mAudioRecord.read(buffer, 0, buffer.length);
                if (0 < length) {
                    // 读取到数据，传递到native层进行音频编码
                    LogUtil.d("音频编码");
                    mPushNative.fireAudio(buffer, length);
                }
            }
        }
    }

}
