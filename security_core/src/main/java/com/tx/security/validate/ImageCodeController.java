package com.tx.security.validate;

import com.tx.security.domain.ImageCode;
import com.tx.security.properties.MercuryProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author ：tx
 * @description： 生成验证码
 * @modified By：
 * @version:
 */
@RestController
public class ImageCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final String SESSION_IMAGE_CODE_KEY = "IMAGE_CODE_KEY";

    @Autowired
    IValidateCodeGenerate imageCodeGenerate;

    @GetMapping("/generate/image-code")
    public void  generateImageCode(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        ImageCode imageCode = imageCodeGenerate.generateImageCode();
        logger.info("当前生成的验证码是：" + imageCode.getCode());
        //把验证码放到session中
        session.setAttribute(SESSION_IMAGE_CODE_KEY,imageCode);
        //输出到页面上
        ImageIO.write(imageCode.getBufferedImage(),"JPEG",response.getOutputStream());
    }
}
