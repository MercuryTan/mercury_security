package com.tx.security.validate;

import com.tx.security.domain.ImageCode;

public interface IValidateCodeGenerate {
    /**
     * 生成验证码
     * @return
     */
    public ImageCode generateImageCode();
}
