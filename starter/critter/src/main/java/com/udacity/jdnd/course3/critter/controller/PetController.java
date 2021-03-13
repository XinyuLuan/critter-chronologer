package com.udacity.jdnd.course3.critter.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.critter.model.dto.PetDTO;
import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.utility.DtoUtility;
import com.udacity.jdnd.course3.critter.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = DtoUtility.convertPetDTO_to_Pet(petDTO);
        Pet addedPet = petService.save(pet, petDTO.getOwnerId());
        return DtoUtility.convertPetToPetDto(addedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.findPetById(petId);
        return DtoUtility.convertPetToPetDto(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findAll();
        return DtoUtility.convertToPetDtoList(pets);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.findPetsByOwner(ownerId);
        return DtoUtility.convertToPetDtoList(pets);
    }
}
