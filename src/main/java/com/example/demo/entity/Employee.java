package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Employee extends AbstractPersistable<Long> {

    private String name;

    @ManyToOne(cascade = CascadeType.ALL,
               fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private Role role;

    static public class EmployeeSpecs {

        public static Specification<Employee> idEqualTo(Long id) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
        }

        public static Specification<Employee> hasRole(final String roleName) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("role").get("name"), roleName);
        }

        public static Specification<Employee> nameStartsWith(final String value) {
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), value+"%");
        }
    }

}
