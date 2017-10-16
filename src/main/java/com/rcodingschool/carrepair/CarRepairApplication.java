package com.rcodingschool.carrepair;

import Miscellaneous.DataGeneration;
import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Repositories.UserRepository;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@ComponentScan("com.rcodingschool.*")
public class CarRepairApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRepairApplication.class, args);
    }
}
