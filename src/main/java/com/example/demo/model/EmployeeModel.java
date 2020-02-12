package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "employee")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeModel extends RepresentationModel<EmployeeModel> {

    private Long id;
    private String name;
    private String role;
}
