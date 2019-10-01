package bg.stefan.demonlp;

import java.io.File;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoNlpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoNlpApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routes(NLPService nlpService) {

		return RouterFunctions.route()
				.POST("/", nlpService::getData).build();
	}

	

	@Bean
	public PropertiesFactoryBean nlpProperties() {
		log.info("properties bean startu");
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		String path = "nlp.properties";

		File f = new File(path);
		if( f.exists() ) { 
			bean.setLocation(new FileSystemResource(f));
		} else { 
			bean.setLocation(new ClassPathResource(path));
		}
		
		return bean;
	}

}
