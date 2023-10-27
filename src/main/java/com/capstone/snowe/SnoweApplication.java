package com.capstone.snowe;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(value = {"com.capstone.snowe.mapper"})
@SpringBootApplication
public class SnoweApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnoweApplication.class, args);
    }

}
