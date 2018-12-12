package com.lmz.mike.eureka;

import com.lmz.mike.common.util.StrU;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	public static void main(String[] args) {

		SpringApplication.run(EurekaApplication.class, args);


		String v="ss";

		System.out.println(StrU.toString(v));

	}
}
