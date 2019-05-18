package com.java.general.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 统一视图跳转控制
 * 允许传递参数类型  url:t1=1&t1=2&t2=3
 * @author : alger
 * @date : 2018/12/28 13:07
 * @version : 1.0.0
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);

    @RequestMapping(value = "/{domain}", method = RequestMethod.GET)
    public String getView(@PathVariable("domain") String domain,
                          HttpServletRequest request) {
        String target = getViewPath(domain);
        JSONObject param = setParams(request);
        LOGGER.info("请求页面:{}>>参数:{}", target, param);
        return target;
    }

    @RequestMapping(value = "/{domain}/{first}", method = RequestMethod.GET)
    public String getView(@PathVariable("domain") String domain, @PathVariable("first") String first,
                          HttpServletRequest request) {
        String target = getViewPath(domain, first);
        JSONObject param = setParams(request);
        LOGGER.info("请求页面:{}>>参数:{}", target, param);
        return target;
    }

    @RequestMapping(value = "/{domain}/{first}/{second}", method = RequestMethod.GET)
    public String getView(@PathVariable("domain") String domain, @PathVariable("first") String first,
                          @PathVariable("second") String second,
                          HttpServletRequest request) {
        String target = getViewPath(domain, first, second);
        JSONObject param = setParams(request);
        LOGGER.info("请求页面:{}>>参数:{}", target, param);
        return target;
    }

    @RequestMapping(value = "/{domain}/{first}/{second}/{third}", method = RequestMethod.GET)
    public String getView(@PathVariable("domain") String domain, @PathVariable("first") String first,
                          @PathVariable("second") String second, @PathVariable("third") String third,
                          HttpServletRequest request) {
        String target = getViewPath(domain, first, second, third);
        JSONObject param = setParams(request);
        LOGGER.info("请求页面:{}>>参数:{}", target, param);
        return target;
    }

    @RequestMapping(value = "/{domain}/{first}/{second}/{third}/{fourth}", method = RequestMethod.GET)
    public String getView(@PathVariable("domain") String domain, @PathVariable("first") String first,
                          @PathVariable("second") String second, @PathVariable("third") String third,
                          @PathVariable("fourth") String fourth,
                          HttpServletRequest request) {
        String target = getViewPath(domain, first, second, third, fourth);
        JSONObject param = setParams(request);
        LOGGER.info("请求页面:{}>>参数:{}", target, param);
        return target;
    }


    /**
     * 请求URL中参数处理
     *
     * @param request 页面请求
     */
    private JSONObject setParams(HttpServletRequest request) {
        JSONObject param = new JSONObject();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String key = paramNames.nextElement();
            String[] values = request.getParameterValues(key);

            if (values == null || values.length == 0) {
                continue;
            }

            if(values.length == 1){
                request.setAttribute(key, values[0]);
                param.put(key, values[0]);
            }else{
                JSONArray valueList= new JSONArray();
                valueList.addAll(Arrays.asList(values));
                request.setAttribute(key, valueList);
                param.put(key, valueList);
            }
        }
        return param;
    }


    /**
     * 获取跳转页面
     *
     * @param arg 地址数组
     * @return  页面地址
     */
    private String getViewPath(String... arg) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String item: arg) {
            stringBuilder.append(File.separator).append(item);
        }
        return stringBuilder.toString();
    }
}
