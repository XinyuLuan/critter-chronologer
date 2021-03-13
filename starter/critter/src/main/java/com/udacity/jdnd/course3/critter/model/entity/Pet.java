package com.udacity.jdnd.course3.critter.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.critter.view.Views;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name="pet")
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Views.Public.class)
    private Long petId;

    @JsonView(Views.Public.class)
    private String type;

    // @Nationalized annotation on every string field to force jpa to create that column as nvarchar. Otherwise the string won't persist correctly.
    @Nationalized
    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
//    private Long ownerId;
//    @ManyToOne(targetEntity = Customer.class)
    @ManyToOne
    private Customer customer;

    @JsonView(Views.Public.class)
    private LocalDate birthDate;

    @JsonView(Views.Public.class)
    private String notes;

    public Pet() {
    }

    public Pet(Long petId, String type, String name, LocalDate birthDate, String notes) {
        this.petId = petId;
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;

    }

//    public long getPetId() {
//        return petId;
//    }
//
//    public void setPetId(Long petId) {
//        this.petId = petId;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setOwner(Customer customer) {
//        this.customer = customer;
//    }
//
//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
}
