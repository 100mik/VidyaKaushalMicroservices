package org.sj.msfilemgmnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MsFileMgmntApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsFileMgmntApplication.class, args);
	}
}
