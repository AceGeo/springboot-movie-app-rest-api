package com.springboot.movie.app.springbootmovieapprestapi;

import com.springboot.movie.app.springbootmovieapprestapi.repository.UserRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Movie App REST APIs with Spring Boot",
				description = "Documentation of Movie App REST APIs with Spring Boot",
				version = "v1.0",
				contact = @Contact(
						name = "Giorgos Postantzoglou",
						email = "george.postantzoglou@gmail.com",
						url = "https://www.linkedin.com/in/georgios-postantzoglou-87a88614b/"
				),
				license = @License(
						name = "MIT",
						url = "https://www.linkedin.com/in/georgios-postantzoglou-87a88614b/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Documentation of Movie App",
				url = "https://github.com/AceGeo?tab=repositories"
		)
)
public class SpringbootMovieAppRestApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMovieAppRestApiApplication.class, args);
	}

}
