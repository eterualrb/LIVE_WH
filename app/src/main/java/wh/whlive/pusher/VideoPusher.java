package wh.whlive.pusher;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.IOException;

import wh.whlive.params.VideoParmas;
import wh.whlive.utils.LogUtil;

public class VideoPusher extends Pusher implements SurfaceHolder.Callback, Camera.PreviewCallback {

    private SurfaceHolder mSurfaceHolder;
    private VideoParmas mVideoParams;

    private Camera mCamera;
    private byte[] mBuffers;

    private boolean mIsPushing = false;

    public VideoPusher(SurfaceHolder holder, VideoParmas videoParams) {
        mSurfaceHolder = holder;
        mVideoParams = videoParams;
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void startPush() {
        mIsPushing = true;
    }

    @Override
    public void stopPush() {
        mIsPushing = false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        startPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        stopPreview();
    }

    private void startPreview() {
        try {
            // surfaceView初始化完成，开始相机预览
            mCamera = Camera.open(mVideoParams.getCameraId());
            mCamera.setPreviewDisplay(mSurfaceHolder);
            mBuffers = new byte[mVideoParams.getWidth() * mVideoParams.getHeight() * 4]; // 设置图像数据缓冲区大小
            mCamera.addCallbackBuffer(mBuffers); // 设置数据缓冲区回调后onPreviewFrame方法会回调
            mCamera.setPreviewCallbackWithBuffer(this); // 获取预览图像数据
            mCamera.startPreview(); // 开始预览
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopPreview() {
        if (null != mCamera) {
            mCamera.stopPreview(); // 停止预览
            mCamera.release();
            mCamera = null;
        }
    }

    public void switchCamera() {
        if (Camera.CameraInfo.CAMERA_FACING_BACK == mVideoParams.getCameraId()) {
            mVideoParams.setCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT);
        } else if (Camera.CameraInfo.CAMERA_FACING_FRONT == mVideoParams.getCameraId()) {
            mVideoParams.setCameraId(Camera.CameraInfo.CAMERA_FACING_BACK);
        }

        stopPreview();
        startPreview();
    }

    @Override
    public void onPreviewFrame(byte[] bytes, Camera camera) {
        LogUtil.d("onPreviewFrame");
        if (mIsPushing) {
            // 开始直播后，在回调方法中获取图像数据，传给native层进行编码
            LogUtil.d("视频编码");
        }

        // 重新设置数据缓冲区回调，onPreviewFrame方法会不断回调，一帧回调一次
        mCamera.addCallbackBuffer(mBuffers);
    }

}
