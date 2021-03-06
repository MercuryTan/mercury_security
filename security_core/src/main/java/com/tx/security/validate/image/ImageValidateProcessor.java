package com.tx.security.validate.image;

import com.tx.security.domain.ImageCode;
import com.tx.security.validate.basic.AbstractValidateProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Component("imageValidateProcessor")
public class ImageValidateProcessor extends AbstractValidateProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
//        ImageCode  imageCode = (ImageCode)code;
        HttpServletResponse response = request.getResponse();
        ImageIO.write(imageCode.getBufferedImage(),"JPEG",response.getOutputStream());
    }
}