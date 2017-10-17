package com.rcodingschool.carrepair;

import Miscellaneous.DataGeneration;
import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Repositories.UserRepository;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@EntityScan(basePackageClasses = {CarRepairApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
@ComponentScan("com.rcodingschool.*")
public class CarRepairApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRepairApplication.class, args);
    }
}
