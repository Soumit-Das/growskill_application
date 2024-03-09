package com.growskill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrowSkillApplication {

	public static void main(String[] args) {
		System.out.println();
		System.out.println("                                  -=-=-=-=-=-=-=-=-=-=-=-=- Application is starting -=-=-=-=-=-=-=-=-=-=-=-=-=-");
		SpringApplication.run(GrowSkillApplication.class, args);
		System.out.println();
		System.out.println("                                 -=-=-=-=-=-=-=-=-=-=-=-=-=- Application is working fine -=-=-=-=-=-=-=-=-=-=-=-=-=-");
	}

}
