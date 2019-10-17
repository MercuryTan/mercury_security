package com.tx.security.validate;

import com.tx.security.properties.MercuryProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class ValidateConfig {

    @Autowired
    MercuryProperty mercuryProperty;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerate") //如果应用中没有找到名字是这个的bean,那么就会生成下面这个bena
    public IValidateCodeGenerate imageCodeGenerate(){
        ImageCodeGenerate imageCodeGenerate = new ImageCodeGenerate();
        imageCodeGenerate.setMercuryProperty(mercuryProperty);
        return imageCodeGenerate;
    }
}