package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CusRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CusService {
    @Autowired
    CusRepository cusRepository;

    @Autowired
    PetRepository petRepository;

    public List<Customer> findAll(){
        return cusRepository.getAllCustomers();
    }

    public Customer findOneById(Long id){
        return cusRepository.getCustomerById(id);
    }

    public Customer findOwnerByPet(Long petId){
        Pet pet = petRepository.getOne(petId);
        return cusRepository.getOwnerByPet(pet);
    }
}
