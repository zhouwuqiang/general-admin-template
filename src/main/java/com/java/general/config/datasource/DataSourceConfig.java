package com.java.general.config.datasource;

import lombok.Getter;
import lombok.Setter;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/5 10:58
 */
@Getter
@Setter
public class DataSourceConfig {

    private String sourceName;

    private String url;

    private String userName;

    private String userPassword;
}
