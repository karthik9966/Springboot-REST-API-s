package com.ttu.blogapplication;

import com.ttu.blogapplication.model.Role;
import com.ttu.blogapplication.model.User;
import com.ttu.blogapplication.repository.RoleRepository;
import com.ttu.blogapplication.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

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
public class BlogApplication {

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
