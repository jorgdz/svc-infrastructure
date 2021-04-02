package com.righttek.us.serverconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ServerConfigUsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerConfigUsApplication.class, args);
	}

}
