package com.springbootrestsso.springbootrestsso;

import com.springbootrestsso.springbootrestsso.entities.Role;
import com.springbootrestsso.springbootrestsso.entities.User;
import com.springbootrestsso.springbootrestsso.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootRestSsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestSsoApplication.class, args);
	}
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository userRepository) throws Exception {
		if(userRepository.count() == 0){
			userRepository.save(new User("user",passwordEncoder.encode("user"), Arrays.asList(new Role("USER"),new Role("ACTUATOR"),new Role("CLIENT"),new Role("TRUSTED_CLIENT")),true));
		}
		builder.userDetailsService(s -> new CustomUserDetails(userRepository.findByUsername(s)));
	}
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
