package com.example.demo.dao;

import com.example.demo.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.*;

@SpringBootTest
@Slf4j
public class EmployeeDaoTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void testSpecs() {
        List<Employee> employes = repository.findAll(where(Employee.EmployeeSpecs.hasRole("thief"))
                                                        .and(Employee.EmployeeSpecs.nameStartsWith("Frod")));
        log.debug(">>> employes: " + employes);

    }
}
