package com.java.general.generator.constant;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/7/6 18:23
 */
public interface TemplateConstant {


    /**
     * 模板类型配置
     */
    interface TemplateType {

        /**
         * 主视图
         */
        String VIEW_MANAGER = "VIEW_MANAGER";

        /**
         * 主视图js
         */
        String VIEW_MANAGER_JS = "VIEW_MANAGER_JS";

        /**
         * controller 类
         */
        String CONTROLLER = "CONTROLLER";

        /**
         * facade 类
         */
        String FACADE = "FACADE";

        /**
         * facade 实现
         */
        String FACADE_IMPL = "FACADE_IMPL";

        /**
         * service 类
         */
        String SERVICE = "SERVICE";

        /**
         * service 实现
         */
        String SERVICE_IMPL = "SERVICE_IMPL";

        /**
         * 保存请求DTO
         */
        String SAVE_REQUEST_DTO = "SAVE_DTO";

        /**
         * table请求DTO
         */
        String TABLE_REQUEST_DTO = "TABLE_REQUEST_DTO";
    }


}
