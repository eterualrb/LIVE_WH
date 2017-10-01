package wh.whlive.params;

public class VideoParmas {

    /**
     * 视频宽
     */
    private int width;
    /**
     * 视频高
     */
    private int height;
    /**
     * 比特率
     */
    private int bitRate = 480000; // 480kbps
    /**
     * 帧频
     */
    private int fps = 25; // 默认25帧/s
    /**
     * 前置or后置摄像头id
     */
    private int cameraId;

    public VideoParmas(int width, int height, int cameraId) {
        this.width = width;
        this.height = height;
        this.cameraId = cameraId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public int getBitRate() {
        return bitRate;
    }

    public void setBitRate(int bitRate) {
        this.bitRate = bitRate;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }
}
