package com.example.demo.config;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Slf4j
public class DbConfig {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {

        Role bulgar = new Role("bulgar");
        Role thief = new Role("thief");
        Role wizzard = new Role("wizzard");
        Role king = new Role("king");
        Role elf = new Role("elf");

        return args -> {
            repository.saveAll(
                    Arrays.asList(new Employee("Bilbo Baggins", bulgar),
                                new Employee("Frodo Baggins", thief),
                                new Employee("Gendalf", wizzard),
                                new Employee("Saruman", wizzard),
                                new Employee("Aragorn", king),
                                new Employee("Legolas", elf))
            );

            log.debug(">>> created " + repository.count() + " entities");

        };
    }
}
