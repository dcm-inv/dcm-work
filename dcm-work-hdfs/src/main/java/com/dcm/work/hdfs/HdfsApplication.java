package com.dcm.work.hdfs;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * HiveSQL执行器
 * @author hourz
 * @since 2021-05-28
 */
@SpringBootApplication(scanBasePackages = "com.dcm.work.hdfs.*")
@MapperScan("com.dcm.work.hive.dao")
@Slf4j
public class HdfsApplication {
	public static void main(String[] args) {
		log.info("启动HiveSQL执行工具开始！");
		SpringApplication.run(HdfsApplication.class, args);
		log.info("启动HiveSQl执行工具结束！");
	}
}
