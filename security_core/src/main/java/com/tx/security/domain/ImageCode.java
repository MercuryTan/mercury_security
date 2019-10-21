package com.tx.security.domain;

import java.awt.image.BufferedImage;

/**
 * @author ：tx
 * @description：验证码实体类
 * @modified By：
 * @version:
 */
public class ImageCode extends ValidateCode {

    private BufferedImage bufferedImage;

    /**
     * @author tx
     * @description
     * @param  * @Param: bufferedImage 图片
     * @Param: code  验证码
     * @Param: effectiveTime  失效时间
     * @return
    **/
    public ImageCode(BufferedImage bufferedImage, String code, int effectiveTime) {
        super(code, effectiveTime);
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    }
