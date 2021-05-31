package com.dcm.work.hive.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 * @author hourz
 * @since 2018-05-21
 */
@Data
@Component
@Configuration
public class DruidConfig {

    @Value("${spring.datasource.master.driver-class-name}")
    private String masterDriverClassName;
    @Value("${spring.datasource.master.type}")
    private String masterType;
    @Value("${spring.datasource.master.url}")
    private String masterUrl;
    @Value("${spring.datasource.master.username}")
    private String masterUsername;
    @Value("${spring.datasource.master.password}")
    private String masterPassword;

    @Value("${spring.datasource.slave.driver-class-name}")
    private String slaveDriverClassName;
    @Value("${spring.datasource.slave.type}")
    private String slaveType;
    @Value("${spring.datasource.slave.url}")
    private String slaveUrl;
    @Value("${spring.datasource.slave.username}")
    private String slaveUsername;
    @Value("${spring.datasource.slave.password}")
    private String slavePassword;

    @Value("${spring.druid.initialSize}")
    private int initialSize;
    @Value("${spring.druid.minIdle}")
    private int minIdle;
    @Value("${spring.druid.maxActive}")
    private int maxActive;
    @Value("${spring.druid.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;
    @Value("${spring.druid.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;
    @Value("${spring.druid.validationQuery}")
    private String validationQuery;
    @Value("${spring.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.druid.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.druid.filters}")
    private String filters;
    @Value("${spring.druid.connectionProperties}")
    private String connectionProperties;

    public DataSource masterTarget() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(masterDriverClassName);
        druidDataSource.setUrl(masterUrl);
        druidDataSource.setUsername(masterUsername);
        druidDataSource.setPassword(masterPassword);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setTimeBetweenConnectErrorMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            druidDataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    public DataSource slaveTarget() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(slaveDriverClassName);
        druidDataSource.setUrl(slaveUrl);
        druidDataSource.setUsername(slaveUsername);
        druidDataSource.setPassword(slavePassword);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setTimeBetweenConnectErrorMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            druidDataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    /**
     * 动态数据源配置
     * @return
     */
    @Bean
    public DataSource dynamicDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        DataSource masterTarget = masterTarget();
        DataSource slaveTarget = slaveTarget();
        targetDataSources.put("master", masterTarget);
        targetDataSources.put("slave", slaveTarget);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterTarget);
        DynamicDataSourceContextHolder.dataSourceIds.add("master");
        DynamicDataSourceContextHolder.dataSourceIds.add("slave");
        return dynamicDataSource;
    }

    /**
     * 配置访问druid监控
     */
    @Bean
    public ServletRegistrationBean druidStateViewServlet() {
        // 初始化参数initParams
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加白名单
        servletRegistrationBean.addInitParameter("allow", "");
        // 添加黑名单
        servletRegistrationBean.addInitParameter("deny", "");
        // 登录查看信息的账号
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        // 登录查看信息的密码
        servletRegistrationBean.addInitParameter("loginPassword", "123");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 过滤不需要监控的后缀
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
