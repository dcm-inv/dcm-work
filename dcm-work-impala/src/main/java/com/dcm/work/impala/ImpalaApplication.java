package com.dcm.work.impala;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dcm.work.impala.*")
@MapperScan("com.dcm.work.impala.dao")
@Slf4j
public class ImpalaApplication {

	public static void main(String[] args) {
		log.info("开始");
		SpringApplication.run(ImpalaApplication.class, args);
	}

}
