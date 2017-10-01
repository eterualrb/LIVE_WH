package wh.whlive.pusher;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import wh.whlive.params.AudioParams;
import wh.whlive.params.VideoParmas;

public class LivePusher implements SurfaceHolder.Callback {

    private SurfaceHolder mSurfaceHolder;
    private VideoPusher mVideoPusher;
    private AudioPusher mAudioPusher;

    public LivePusher(SurfaceHolder holder) {
        mSurfaceHolder = holder;
        mSurfaceHolder.addCallback(this);
        prepare();
    }

    private void prepare() {
        // 初始化音视频推流器
        VideoParmas videoParams = new VideoParmas(480, 320, Camera.CameraInfo.CAMERA_FACING_BACK);
        mVideoPusher = new VideoPusher(mSurfaceHolder, videoParams);
        AudioParams audioParams = new AudioParams();
        mAudioPusher = new AudioPusher(audioParams);
    }

    /**
     * 切换摄像头
     */
    public void switchCamera() {
        mVideoPusher.switchCamera();
    }

    /**
     * 开始直播
     */
    public void startPush() {
        mVideoPusher.startPush();
        mAudioPusher.startPush();
    }

    /**
     * 停止直播
     */
    public void stopPush() {
        mVideoPusher.stopPush();
        mAudioPusher.stopPush();
    }

    private void release() {
        mVideoPusher.release();
        mAudioPusher.release();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        stopPush();
        release();
    }

}
