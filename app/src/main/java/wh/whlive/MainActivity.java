package wh.whlive;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import wh.whlive.pusher.LivePusher;

public class MainActivity extends Activity {

    /**
     * 推流地址
     */
//    private String url = "rtmp://192.168.1.105:1935/liveA/room";
    private String url = "rtmp://172.16.71.52:1935/liveA/room";

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
        Button btn = (Button) view;
        if ("开始直播".equals(btn.getText().toString())) {
            mLivePusher.startPush(url);
            btn.setText("停止直播");
        } else {
            mLivePusher.stopPush();
            btn.setText("开始直播");
        }
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
