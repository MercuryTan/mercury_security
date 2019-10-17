package com.tx.security.broswer.pojo;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ：tx
 * @description：验证码实体类
 * @modified By：
 * @version:
 */
public class ImageCode {

    private BufferedImage bufferedImage;

    private String code;

    private LocalDateTime failureTime;

    /**
     * @author tx
     * @description
     * @param  * @Param: bufferedImage 图片
     * @Param: code  验证码
     * @Param: effectiveTime  失效时间
     * @return
    **/
    public ImageCode(BufferedImage bufferedImage, String code, int effectiveTime) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.failureTime = LocalDateTime.now().plusSeconds(effectiveTime);
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(LocalDateTime failureTime) {
        this.failureTime = failureTime;
    }
}
