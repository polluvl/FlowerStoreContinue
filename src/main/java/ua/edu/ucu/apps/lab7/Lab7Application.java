package ua.edu.ucu.apps.lab7;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController // access our data via http controllers: GET, POST, PUT, DELETE, PATCH, OPTIONS
public class Lab7Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab7Application.class, args);
	}

	@GetMapping("/list")
	public List<String> getRandomUUID(){
		// Rest must satisfy specific rules: idempotency (consistent state) 
		// do the endpoint to return a list or random uuiid:
		UUID.randomUUID();
		return Stream.generate(()-> UUID.randomUUID())
		.map(UUID:: toString)
		.limit(10)
		.toList();

	}

}
