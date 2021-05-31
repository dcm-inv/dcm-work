package com.dcm.work.presto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dcm.work.presto.*")
@MapperScan("com.dcm.work.presto.dao")
public class PrestoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrestoApplication.class, args);
	}

}
