package org.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.anno.ArticleState;
import org.example.entity.Article;

public class StateValidation implements ConstraintValidator<ArticleState, String> {
    /**
     * 验证逻辑
     *
     * @param value  需要验证的值
     * @param context 上下文
     * @return false 表示验证失败，true表示验证成功
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null)
            return false;
        if(value.equals("已发布")||value.equals("草稿"))
            return true;
        return false;
    }
}
