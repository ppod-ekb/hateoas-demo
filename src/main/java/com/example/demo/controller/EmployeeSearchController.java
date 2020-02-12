package com.example.demo.controller;

import com.example.demo.assembler.EmployeeModelAssembler;
import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;
import com.example.demo.model.EmployeeModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@AllArgsConstructor
public class EmployeeSearchController {
/*
*
Representation models
The ResourceSupport / Resource / Resources / PagedResources group of classes never really felt appropriately named. After all, these types do not actually manifest resources but rather representation models that can be enriched with hypermedia information and affordances. Here’s how new names map to the old ones:
ResourceSupport is now RepresentationModel
Resource is now EntityModel
Resources is now CollectionModel
PagedResources is now PagedModel
*
* */
    private final EmployeeRepository repository;
    private final EmployeeModelAssembler assembler;

    @Autowired
    private PagedResourcesAssembler<Employee> pagedResourcesAssembler;

    @GetMapping("/api/employees/spec/findById")
    public ResponseEntity<EmployeeModel> findById(@RequestParam Long id) {
        return repository.findOne(Employee.EmployeeSpecs.idEqualTo(id))
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    };

    @GetMapping("/api/employees/spec/findByRole")
    public ResponseEntity<CollectionModel<EmployeeModel>> findByRole(@RequestParam String role) {
        List<Employee> employes = repository.findAll(Employee.EmployeeSpecs.hasRole(role));
        return new ResponseEntity<CollectionModel<EmployeeModel>>(
                assembler.toCollectionModel(employes), HttpStatus.OK);
    }

    @GetMapping("/api/employees/spec/findByRoleWithPage")
    public ResponseEntity<PagedModel<EmployeeModel>> findByRoleWithPage(@RequestParam String role, Pageable pageable) {
        Page<Employee> employes = repository.findAll(Employee.EmployeeSpecs.hasRole(role), pageable);
        return new ResponseEntity<PagedModel<EmployeeModel>>(
                pagedResourcesAssembler.toModel(employes, assembler), HttpStatus.OK);
    }

}
