package com.java.general.validation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: alger
 * @date: 2018/12/21 11:26
 * @version: 1.0.0
 */
@Setter
@Getter
@ToString
public class ValidResult {

    /**
     * 是否有错误
     */
    private boolean hasErrors;

    /**
     * 错误信息
     */
    private List<ErrorMessage> errors = new ArrayList<>();


    public void addError(String property, String message) {
        this.errors.add(new ErrorMessage(property,message));
    }

    @Setter
    @Getter
    @ToString
    public class ErrorMessage {

        /**
         * 异常属性
         */
        private String property;

        /**
         * 异常消息
         */
        private String message;

        public ErrorMessage() {
        }

        public ErrorMessage(String property, String message) {
            this.property = property;
            this.message = message;
        }
    }

}
