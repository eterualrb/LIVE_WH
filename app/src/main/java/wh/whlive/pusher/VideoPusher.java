package wh.whlive.pusher;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.IOException;

import wh.whlive.params.VideoParmas;

public class VideoPusher extends Pusher implements SurfaceHolder.Callback {

    private SurfaceHolder mSurfaceHolder;
    private VideoParmas mVideoParams;
    private Camera mCamera;

    public VideoPusher(SurfaceHolder holder, VideoParmas videoParams) {
        mSurfaceHolder = holder;
        mVideoParams = videoParams;
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void startPush() {

    }

    @Override
    public void stopPush() {

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

}
