package com.umc9th.umc9th;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.swing.*;

@SpringBootApplication
@EnableJpaAuditing
public class Umc9thApplication {

	public static void main(String[] args) {
		SpringApplication.run(Umc9thApplication.class, args);
	}

}
