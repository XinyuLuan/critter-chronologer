package com.udacity.jdnd.course3.critter.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.critter.view.Views;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
@Entity
@Table
@Data
@NoArgsConstructor
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private String notes;

    @OneToMany(targetEntity = Pet.class)
//    @OneToMany
//    @JoinColumn(
//            name = "customer_id",
//            referencedColumnName = "id",
//            insertable = false,
//            updatable = false,
//            foreignKey = @javax.persistence
//                    .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private List<Pet> pets;

    public void insertPet(Pet pet){
        if(pets == null){
            pets = new ArrayList<>();
        }
        pets.add(pet);
    }

}
