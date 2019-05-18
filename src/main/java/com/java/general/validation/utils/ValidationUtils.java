package com.java.general.validation.utils;

import com.java.general.validation.dto.ValidResult;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @description:
 * 接口入参数据校验工具类
 * 使用hibernate-validator进行校验.
 * @Null 被注释的元素必须为 null
 * @NotNull 被注释的元素必须不为 null
 * @AssertTrue 被注释的元素必须为 true
 * @AssertFalse 被注释的元素必须为 false
 * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
 * @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
 * @Past 被注释的元素必须是一个过去的日期
 * @Future 被注释的元素必须是一个将来的日期
 * @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
 * Hibernate Validator 附加的 constraint
 * @NotBlank(message =)   验证字符串非null，且长度必须大于0
 * @Email 被注释的元素必须是电子邮箱地址
 * @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
 * @NotEmpty 被注释的字符串的必须非空
 * @Range(min=,max=,message=) 被注释的元素必须在合适的范围内
 * @author: alger
 * @date: 2018/12/21 11:16
 * @version: 1.0.0
 */
public class ValidationUtils {
    /**
     * 开启快速结束模式 failFast (true)
     */
    private static Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 分组校验bean
     *
     * @param t bean
     * @param groups 校验组
     * @return ValidResult
     */
    public static <T> ValidResult validateGroup(T t, Class<?>...groups) {
        Set<ConstraintViolation<T>> violationSet = validator.validate(t,groups);
        return bindResult(violationSet);
    }


    /**
     * 校验bean的某一个属性
     *
     * @param obj          bean
     * @param propertyName 属性名称
     * @return ValidResult
     */
    public static <T> ValidResult validateProperty(T obj, String propertyName) {
        Set<ConstraintViolation<T>> violationSet = validator.validateProperty(obj,propertyName);
        return bindResult(violationSet);
    }


    /**
     * 绑定校验结果
     * @param constraintViolationSet
     * @param <T>
     * @return
     */
    private static  <T> ValidResult bindResult(Set<ConstraintViolation<T>> constraintViolationSet){
        ValidResult result = new ValidResult();
        boolean hasError = constraintViolationSet != null && constraintViolationSet.size() > 0;
        if (hasError) {
            for (ConstraintViolation<T> violation : constraintViolationSet) {
                result.addError(violation.getPropertyPath().toString(), violation.getMessage());
            }
            result.setHasErrors(true);
        }
        return result;
    }
}
