package com.dcm.work.hive.config;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static List<String> dataSourceIds = new ArrayList<>();

    public static ThreadLocal<String> getContextHolder() {
        return contextHolder;
    }

    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }

}
