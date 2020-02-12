package com.example.demo.assembler;

import com.example.demo.controller.EmployeeSearchController;
import com.example.demo.entity.Employee;
import com.example.demo.model.EmployeeModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;

@Component
public class EmployeeModelAssembler extends RepresentationModelAssemblerSupport<Employee, EmployeeModel> {


    public EmployeeModelAssembler() {
        super(EmployeeSearchController.class, EmployeeModel.class);
    }

    @Override
    public CollectionModel<EmployeeModel> toCollectionModel(Iterable<? extends Employee> entities) {
        CollectionModel<EmployeeModel> collectionModel = super.toCollectionModel(entities);

        Link currentRequest = new Link(ServletUriComponentsBuilder.fromCurrentRequest().build().toString());
        //UriComponentsBuilder builder = linkTo(EmployeeSearchController.class).toUriComponentsBuilder();
        //new Link("", LinkRelation.of("/api/employes"));

        collectionModel.add(
                linkTo(
                        methodOn(EmployeeSearchController.class).findByRole(null))
                        .withRel("findByRole")
        ).add(  linkTo(
                methodOn(EmployeeSearchController.class).findByRoleWithPage(null, null))
                .withRel("findByRoleWithPage"))
        .add(currentRequest)
        .add(new Link(ServletUriComponentsBuilder.fromCurrentRequestUri().build("a", "b").toString(), "test"))
        //.add(linkTo(methodOn(EmployeeSearchController.class, "role","b","c").findByRoleWithPage(null,null)).withRel("test2")) ;
        ;


        return collectionModel;
    }

    private String currentRequestUrl() {
        return ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
    }

    @Override
    public EmployeeModel toModel(Employee entity) {
        EmployeeModel employeeModel = instantiateModel(entity);

        employeeModel.add(
                linkTo(
                methodOn(EmployeeSearchController.class)
                        .findById(entity.getId()))
                .withRel("findById"))
        .add(new Link(currentRequestUrl()))
        ;

       /* employeeModel.add(linkTo(
                methodOn(EmployeeSearchController.class).findByRole(entity.getRole()))
                .withRel("findByRole"));*/

        employeeModel.setId(entity.getId());
        employeeModel.setName(entity.getName());
        employeeModel.setRole(entity.getRole().getName());
        return employeeModel;
    }
}
