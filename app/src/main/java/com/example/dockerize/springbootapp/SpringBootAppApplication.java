package com.example.dockerize.springbootapp;

import com.example.dockerize.springbootapp.document.Users;
import com.example.dockerize.springbootapp.repository.ElasticRepository;
import com.example.dockerize.springbootapp.repository.UserRepository;
import java.time.Instant;
import java.util.Date;
import java.util.Random;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class SpringBootAppApplication {


	//private final ElasticRepository elasticRepository;
	private final UserRepository userRepository;

	public SpringBootAppApplication(//ElasticRepository elasticRepository,
		UserRepository userRepository) {
		this.userRepository = userRepository;
		//this.elasticRepository = elasticRepository;
	}

	@GetMapping("/hello")
	public String hello() {
		try {
			//addElastic();;
			addMongo();
		} catch (Exception e) {
			return "Error occurred: " + e.getMessage();
		}
		return "good!";
	}

	private String getMongoDBUsers() {
		var users = userRepository.findAll();
		var result = new StringBuilder();
		for (Object user : users) {
			result.append(user.toString()).append("\n");
		}
		return result.toString();
	}

/*
	public void addElastic() {
		var r = new Random();
		var id = r.nextInt() * 1000;
		for(int i = 0; i < 10; i++) {
			var o = new OrderLog();
			o.setId(String.valueOf(id + i));
			o.setTimestamp(Date.from(Instant.EPOCH));
			elasticRepository.save(o);
		}
	}
*/

	public void addMongo() {
		var r = new Random();
		var id = r.nextInt() * 1000;
		for (int i = 0; i < 10; i++) {
			var u = new Users(id, "a" + i, "b" + i, 100L);
			userRepository.save(u);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}
}
