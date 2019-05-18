package com.java.general.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.java.general.exception.BusinessException;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description :
 * 动态数据钩子获取动态的数据源
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/4 11:09
 */
public class MultipleDataSource extends AbstractRoutingDataSource {


    private static final InheritableThreadLocal<String> DATA_SOURCE_KEY = new InheritableThreadLocal<String>();


    /**
     * 设置线程使用数据库
     * @param dataSource
     */
    public static void setDataSourceKey(String dataSource) {
        DATA_SOURCE_KEY.set(dataSource);
    }

    /**
     * 获取当前线程的数据库
     * @return
     */
    public static String getDataSourceKey() {
        return DATA_SOURCE_KEY.get();
    }

    /**
     * 重置数据库访问连接
     */
    public static void removeDataSourceKey() {
      DATA_SOURCE_KEY.remove();
    }


    @Override
    protected Object determineCurrentLookupKey() {
        return DATA_SOURCE_KEY.get();
    }


    /**
     * 刷新数据库连接
     *
     * @param dataSourceList
     */
    public void refreshDataSource(List<DataSourceConfig> dataSourceList){
        try {
            Map<Object, Object> targetDataSources = new HashMap<>();
            for (DataSourceConfig config : dataSourceList) {
                DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
                dataSource.setUrl(config.getUrl());
                dataSource.setUsername(config.getUserName());
                dataSource.setPassword(config.getUserPassword());
                dataSource.setFilters("stat,wall,log4j");
                targetDataSources.put(config.getSourceName(), dataSource);
            }
            DataSource defaultDateSource = determineTargetDataSource();
            targetDataSources.put("default",defaultDateSource);
            setDefaultTargetDataSource(defaultDateSource);
            setTargetDataSources(targetDataSources);
            afterPropertiesSet();
        }catch (Exception e){
            throw new BusinessException("刷新数据库信息异常");
        }
    }

}
