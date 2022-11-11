package com.example.challenge.s1_challenge;

import com.example.challenge.s1_challenge.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class S1ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(S1ChallengeApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean filterBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/userdata/v1/*");
		return filterRegistrationBean;
	}

}
