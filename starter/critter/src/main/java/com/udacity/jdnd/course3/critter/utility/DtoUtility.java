package com.udacity.jdnd.course3.critter.utility;

import com.udacity.jdnd.course3.critter.model.dto.*;
import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.model.entity.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DtoUtility {
    public static PetDTO convertPetToPetDto(Pet pet) {
        return new PetDTO(pet.getPetId(), PetType.valueOf(pet.getType()), pet.getName(), pet.getCustomer().getId(), pet.getBirthDate(), pet.getNotes());
    }

    public static Pet convertPetDTO_to_Pet(PetDTO dto){
        return new Pet(dto.getId(), dto.getType().toString(), dto.getName(), dto.getBirthDate(), dto.getNotes() );
    }

    public static List<PetDTO> convertToPetDtoList(List<Pet> petList) {
        List<PetDTO> list = new ArrayList<>();
        for(Pet pet: petList){
            PetDTO temp = convertPetToPetDto(pet);
            list.add(temp);
        }
        return list;
    }

    public static List<Pet> convertPetDTO_to_petList(List<PetDTO> petDTOList) {
        List<Pet> list = new ArrayList<>();
        for(PetDTO dto: petDTOList){
            Pet temp = convertPetDTO_to_Pet(dto);
            list.add(temp);
        }
        return list;
    }

    public static CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setId(customer.getId());
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setPetIds(customer.getPets());
        
        return customerDTO;
    }

    public static Employee convertToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();

        employee.setName(employeeDTO.getName());
        employee.setSkills(employeeDTO.getSkills());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());

        return employee;
    }

    public static EmployeeDTO convertToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSkills(employee.getSkills());

        return employeeDTO;
    }

    public static ScheduleDTO convertToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toList()));
        scheduleDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getPetId()).collect(Collectors.toList()));

        return scheduleDTO;
    }
}
