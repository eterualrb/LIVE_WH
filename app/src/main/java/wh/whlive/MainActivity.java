package wh.whlive;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;

import wh.whlive.pusher.LivePusher;

public class MainActivity extends Activity {

    private LivePusher mLivePusher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurfaceView surfaceView = findViewById(R.id.surface);

        // 相机图像的预览
        mLivePusher = new LivePusher(surfaceView.getHolder());
    }

    /**
     * 点击开始直播
     *
     * @param view
     */
    public void onClickStartLive(View view) {

    }

    /**
     * 点击切换摄像头
     *
     * @param view
     */
    public void onClickSwitchCamera(View view) {
        mLivePusher.switchCamera();
    }

}