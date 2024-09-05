package com.bootcamp.eventorg;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

		log.info("Application running ");

	}
//	@Bean
//	CommandLineRunner runner(
//	){
//		return args->{
//			Event event = new Event(1,"Konser A", LocalDateTime.now(),LocalDateTime.now().plusHours(5),
//					10000, Location.JAKARTA);
//			log.info("event: "+event);
//		};
	}



