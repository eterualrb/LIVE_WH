package wh.whlive.pusher;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import wh.whlive.params.VideoParmas;

public class LivePusher {

    private SurfaceHolder mSurfaceHolder;
    private VideoPusher mVideoPusher;
    private AudioPusher mAudioPusher;

    public LivePusher(SurfaceHolder holder) {
        mSurfaceHolder = holder;
        prepare();
    }

    private void prepare() {
        // 初始化音视频推流器
        VideoParmas videoParams = new VideoParmas(480, 320, Camera.CameraInfo.CAMERA_FACING_BACK);
        mVideoPusher = new VideoPusher(mSurfaceHolder, videoParams);
        mAudioPusher = new AudioPusher();
    }

    public void switchCamera() {
        mVideoPusher.switchCamera();
    }

}
