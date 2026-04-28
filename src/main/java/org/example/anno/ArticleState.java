package org.example.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validation.StateValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {StateValidation.class })
@Target({  FIELD })
@Retention(RUNTIME)
public @interface ArticleState {
    // 自定义注解的提示信息
    String message() default "状态只能是已发布或草稿";
    // 分组
    Class<?>[] groups() default { };
    // 负载
    Class<? extends Payload>[] payload() default { };


}
