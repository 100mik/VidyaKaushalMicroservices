package org.sj.msserviceregistryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class MsServiceRegistryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsServiceRegistryServerApplication.class, args);
	}

}

