package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.dto.PetDTO;
import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.utility.DtoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerService customerService;

    public Pet save(Pet pet, Long ownerId){
        Customer owner = customerService.getCustomerById(ownerId);
        pet.setCustomer(owner);

        Pet newPet = petRepository.save(pet);
        owner.insertPet(newPet);
        customerService.save(owner);

        return newPet;
    }

    public Pet findPetById(Long id){
        return petRepository.getOne(id);
    }

    public List<Pet> findAll(){
        return petRepository.findAll();
    }

    public List<Pet> findPetsByOwner(Long ownerId){
        return petRepository.findPetsByCustomerId(ownerId);
    }
}
