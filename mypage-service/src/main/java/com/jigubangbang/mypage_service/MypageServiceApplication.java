package com.jigubangbang.mypage_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MypageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MypageServiceApplication.class, args);
	}

}
