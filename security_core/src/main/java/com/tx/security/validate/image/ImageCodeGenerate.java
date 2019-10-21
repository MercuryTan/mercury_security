package com.tx.security.validate.image;

import com.tx.security.domain.ImageCode;
import com.tx.security.properties.MercuryProperty;
import com.tx.security.validate.basic.IValidateCodeGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 */
@Component("imageCodeGenerate")
public class ImageCodeGenerate implements IValidateCodeGenerate {

    @Autowired
    MercuryProperty mercuryProperty;

    @Override
    public ImageCode generateCode() {
        // 图片的宽高（像素）
        int width = mercuryProperty.getCode().getImageCode().getWidth();
        int height = mercuryProperty.getCode().getImageCode().getHeight();
        // 生成图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        // 生成随机条纹干扰
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, xl, yl);
        }

        // 生成四位随机数
        String sRand = "";
        for (int i = 0; i < mercuryProperty.getCode().getImageCode().getCodeLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }
        g.dispose();

        // 60秒有效
        return new ImageCode(image, sRand, mercuryProperty.getCode().getImageCode().getExpireTime());
    }

    /**
     * 生成随机背景条纹
     * @param fc
     * @param bc
     * @return
     */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if(fc > 255) {
            fc = 255;
        }
        if(bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}