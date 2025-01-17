package com.iview;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Interview Tracker API", version = "1.0"))
public class UserManagementServiceApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(UserManagementServiceApplication.class, args);
		// List all registered beans
		String[] beanNames = ctx.getBeanDefinitionNames();
//		for (String beanName : beanNames) {
//			System.out.println("Registered Bean: " + beanName);
//		}
	}

}
