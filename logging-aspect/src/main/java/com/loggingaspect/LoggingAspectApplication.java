package com.loggingaspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoggingAspectApplication {

	@Autowired
	Method autowiredMethod;

	public static void main(String[] args) {

		SpringApplication.run(LoggingAspectApplication.class, args);
	}

	/**
	 * Combo for autowire, bean and configuration paradox resolution
	 * @param ctx
	 * @return
	 */
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			//System.out.println("Let's inspect the beans provided by Spring Boot:");
			Method method = (Method)ctx.getBean("method");
			//Method method=new Method();
			//autowiredMethod.power(2,7);

			Data normalData = new Data(2,4);
			normalData.getNumber();
			System.out.println(method.other(normalData));
			method.power(2,2);
			Data data = ctx.getBean(Data.class);
			method.other(data);
		};
	}
}
