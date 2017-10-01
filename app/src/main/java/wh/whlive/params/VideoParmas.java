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

}
