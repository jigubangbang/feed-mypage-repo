package com.jigubangbang.mypage_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.jigubangbang.mypage_service.mapper")
public class MypageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MypageServiceApplication.class, args);
	}

}
