package com.dcm.work.hive.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.lang.reflect.Field;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        try {
            //通过反射获取父类数据源Map
            Field targetDataSources = this.getClass().getSuperclass().getDeclaredField("targetDataSources");
            targetDataSources.setAccessible(true);
            Map<Object, Object> value = null;
            value = (Map<Object, Object>) targetDataSources.get(this);
            String ds = DynamicDataSourceContextHolder.getDataSource();
            if (ds != null) {
                System.out.println("当前数据源：" + ds + ",url:" + ((DruidDataSource) value.get(ds)).getUrl());
            }
            return DynamicDataSourceContextHolder.getDataSource();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "ds1";
    }
}

