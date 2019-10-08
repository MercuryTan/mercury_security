package com.tx.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <MyValidation,Object>
 * MyValidation：注解是哪个
 * Object: 放在object类型的字段上会起作用
 */
public class MyValidationAlias implements ConstraintValidator<MyValidation,Object> {
    //可以使用其余类，来做逻辑判断
    //@Autowired
    // HelloService helloService;
    @Override
    public void initialize(MyValidation myValidation) {
        System.out.println("自定义校验注解初始化啦~");
    }
    /**
     * @param o 当前校验的字段
     * @param constraintValidatorContext
     */
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(o);
        return false;
    }
}