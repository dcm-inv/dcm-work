package com.dcm.work.hdfs.config;

import com.dcm.work.hdfs.client.HadoopClient;
import com.dcm.work.hdfs.props.HadoopProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

/**
 * @author hourz
 * @since 2021-06-01
 */
@Configuration
@ConditionalOnProperty(name="hadoop.name-node")
@Slf4j
public class HadoopConfig {

    /**
     * 配置
     */
    public org.apache.hadoop.conf.Configuration getConfiguration(HadoopProperties hadoopProperties) {
        //读取配置文件
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        conf.set("dfs.replication", "1");
        conf.set("fs.defaultFS", hadoopProperties.getNameNode());
        conf.set("mapred.job.tracker", hadoopProperties.getNameNode());
        return conf;
    }

    @Bean
    public FileSystem fs(HadoopProperties hadoopProperties){
        // 文件系统
        FileSystem fs = null;
        try {
            URI uri = new URI(hadoopProperties.getDirectoryPath().trim());
            fs = FileSystem.get(uri, this.getConfiguration(hadoopProperties));
        } catch (Exception e) {
            log.error("【FileSystem配置初始化失败】", e);
        }
        return fs;
    }

    @Bean
    @ConditionalOnBean(FileSystem.class)
    public HadoopClient hadoopClient(FileSystem fs, HadoopProperties hadoopProperties) {
        return new HadoopClient(fs, hadoopProperties);
    }
}
