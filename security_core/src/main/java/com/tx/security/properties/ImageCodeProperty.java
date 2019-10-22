package com.tx.security.properties;

/**
 *
 */
public class ImageCodeProperty extends  BasicCodeProperty{

    public ImageCodeProperty() {
        setCodeLength(4);
    }

    /**
     * 图片的宽度
     */
    private int width = 67;

    /**
     * 图片的高度
     */
    private int height = 23;


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

}