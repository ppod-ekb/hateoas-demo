package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role extends AbstractPersistable<Long> {

    private String name;
}
