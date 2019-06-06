package com.java.general.response.utils;


import com.java.general.response.code.ResponseCode;
import com.java.general.response.code.impl.ResponseEnum;
import com.java.general.response.dto.ResponseDto;

/**
 * description: 封装请求返回对象工具类 restful对象返回封装.
 * @author alger
 * @date 2018/12/19 17:07
 * @version 1.0.0
 */
public class ResponseUtil {

    /**
     * 绑定成功返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> bindSuccessResponse() {
        return bindResponseEnum(ResponseEnum.SUCCESS);
    }

    /**
     * 绑定成功返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> bindSuccessResponse(T data) {
        ResponseDto<T> responseData = bindResponseEnum(ResponseEnum.SUCCESS);
        responseData.setData(data);
        return responseData;
    }


    /**
     * 绑定已接收返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> bindReceivedResponse() {
        return bindResponseEnum(ResponseEnum.RECEIVED);
    }

    /**
     * 绑定已接收返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> bindReceivedResponse(T data) {
        ResponseDto<T> responseData = bindResponseEnum(ResponseEnum.RECEIVED);
        responseData.setData(data);
        return responseData;
    }

    /**
     * 绑定失败返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> bindFailResponse() {
        return bindResponseEnum(ResponseEnum.FAIL);
    }

    /**
     * 绑定失败返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> bindFailResponse(T data) {
        ResponseDto<T> responseData = bindResponseEnum(ResponseEnum.FAIL);
        responseData.setData(data);
        return responseData;
    }

    /**
     * 绑定成功返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> bindValidFailResponse() {
        return bindResponseEnum(ResponseEnum.PARAM_ERROR);
    }

    /**
     * 绑定成功返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> bindValidFailResponse(T data) {
        ResponseDto<T> responseData = bindResponseEnum(ResponseEnum.PARAM_ERROR);
        responseData.setData(data);
        return responseData;
    }


    /**
     * 自定义返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> bindFailResponse(ResponseCode item, T data) {
        ResponseDto<T> responseData = bindResponseEnum(item);
        responseData.setData(data);
        return responseData;
    }

    /**
     * 绑定返回枚举值
     *
     * @param item
     * @return
     */
    public static <T> ResponseDto <T> bindResponseEnum(ResponseCode item) {
        ResponseDto <T> response = new ResponseDto<>();
        response.setCode(item.getCode());
        response.setMessage(item.getMessage());
        response.setSuccess(item.isSuccess());
        return response;
    }


}
