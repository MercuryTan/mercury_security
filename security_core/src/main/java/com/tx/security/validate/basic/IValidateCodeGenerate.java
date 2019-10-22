package com.tx.security.validate.basic;

import com.tx.security.domain.ValidateCode;

public interface IValidateCodeGenerate {

    String SESSION_CODE_KEY = "CODE_KEY_";

    /**
     * 生成验证码
     * @return
     */
    ValidateCode generateCode();
}
