package com.example.demo.dao;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    @Query("select e from Employee e join Role r on e.role.id = r.id where r.name = :role")
    List<Employee> findByRole(@Param("role") String role);

    @Query("select e from Employee e where e.name like %:name%")
    Page<Employee> findByName(@Param("name") String name, Pageable pageable);

    @Query(value = "select e from Employee e join Role r on e.role.id = r.id where r.name = :role")
    Page<Employee> findByRoleWithPages(@Param("role") String role, Pageable pageRequest);

    @Query("select e from Employee e join Role r on e.role.id = r.id where r.name = :role and e.name = :name")
    List<Employee> findByRoleAndName(@Param("role") String role, @Param("name") String name) ;
}
