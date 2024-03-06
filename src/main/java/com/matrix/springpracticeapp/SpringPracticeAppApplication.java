package com.matrix.springpracticeapp;

import com.matrix.springpracticeapp.mapper.exampleOfModelMapper.ExampleModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPracticeAppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringPracticeAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ExampleModelMapper.simpleModelMapper();
        System.out.println("======");
        ExampleModelMapper.propertyModelMapper();
    }
}
