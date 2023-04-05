package com.ttu.blogapplication;
import com.ttu.blogapplication.model.Role;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Rest API's for Blog application",
				 contact = @Contact(
						 name = "Karthik",
						 email = "nmarri@ttu.edu"
				 )
		)
)
@SpringBootApplication
public class BlogApplication  {

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
