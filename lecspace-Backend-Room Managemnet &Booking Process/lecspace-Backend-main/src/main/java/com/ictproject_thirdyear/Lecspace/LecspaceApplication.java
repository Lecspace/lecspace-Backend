package com.ictproject_thirdyear.Lecspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.ictproject_thirdyear.Lecspace.entity")
public class LecspaceApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(LecspaceApplication.class, args);
	}

}
