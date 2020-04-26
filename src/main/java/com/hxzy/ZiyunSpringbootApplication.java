package com.hxzy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.hxzy.mapper")
@SpringBootApplication
public class ZiyunSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZiyunSpringbootApplication.class, args);
	}

}
