package bg.stefan.demonlp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@SpringBootApplication
public class DemoNlpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoNlpApplication.class, args);
	}
	
	@Bean
	RouterFunction<ServerResponse> routes(NLPService nlpService) { 
		
		return RouterFunctions.route()
				
				.GET("/", nlpService::getData)
				.build();
	}

}
