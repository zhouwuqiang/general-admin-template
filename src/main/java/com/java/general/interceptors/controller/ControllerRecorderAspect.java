package com.java.general.interceptors.controller;

import com.alibaba.fastjson.JSON;
import com.java.general.interceptors.utils.AspectUtils;
import com.java.general.response.utils.ResponseUtil;
import com.java.general.validation.dto.ValidResult;
import com.java.general.validation.utils.ValidationUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/15 16:04
 */

@Aspect
@Component
public class ControllerRecorderAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerRecorderAspect.class);

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.java.general.interceptors.controller.ControllerRecorder)")
    public void controllerRecorderAspect() {
    }

    /**
     * 环绕通知：灵活自由的在目标方法中切入代码
     *
     * @param joinPoint
     */
    @Around("controllerRecorderAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        ControllerRecorder controllerRecorder = AspectUtils.getDeclaredAnnotation(joinPoint, ControllerRecorder.class);
        Class<?> validateClass = controllerRecorder.validateClass();

        Object[] params = joinPoint.getArgs();

        //没有参数的方法
        boolean isEmptyParam = validateClass.equals(Object.class) && (params == null || params.length == 0);
        if (isEmptyParam) {
            LOGGER.info("{} >> requestDto:not parameters", controllerRecorder.path());
            Object object = joinPoint.proceed();
            LOGGER.info("{} >> responseDto:{}", controllerRecorder.path(), JSON.toJSONString(object));
            return object;
        }


        Object requestDto = new Object();
        for (Object temp : params) {
            if (temp.getClass().equals(validateClass)) {
                requestDto = temp;
            }
        }

        ValidResult validResult;
        if (Object.class != controllerRecorder.validateTypeClass()) {
            validResult = ValidationUtils.validateGroup(requestDto, controllerRecorder.validateTypeClass());
        } else {
            validResult = ValidationUtils.validateGroup(requestDto);
        }

        if (validResult.isHasErrors()) {
            LOGGER.info("{} >> 参数校验错误:{},传入参数{}", controllerRecorder.path(), validResult.getErrors(), JSON.toJSONString(requestDto));
            return ResponseUtil.bindValidFailResponse(validResult.getErrors());
        }


        LOGGER.info("{} >> requestDto:{}", controllerRecorder.path(), JSON.toJSONString(requestDto));
        Object object = joinPoint.proceed();
        LOGGER.info("{} >> responseDto:{}", controllerRecorder.path(), JSON.toJSONString(object));
        return object;
    }

}
