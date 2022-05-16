package com.example.booksapplication;

import com.example.booksapplication.dao.entity.UserEntity;
import com.example.booksapplication.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;


@SpringBootApplication
public class BooksApplication  {



    public static void main(String[] args) {
        SpringApplication.run(BooksApplication.class, args);

    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }



}

