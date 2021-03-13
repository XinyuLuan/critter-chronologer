package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.model.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Schedule save(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAll(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(Long petId){
        Pet pet = petRepository.getOne(petId);
        return scheduleRepository.getAllByPetsContains(pet);
    }

    public List<Schedule> getSchedulesForEmployee(Long employeeId){
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.getAllByEmployeesContains(employee);
    }

    public List<Schedule> getSchedulesForCustomer(Long customerId){
        List<Pet> pets = petRepository.findPetsByCustomerId(customerId);
        List<Schedule> schedules = scheduleRepository.getAllByPetsIn(pets);
        return schedules;
    }
//
//    @GetMapping("/customer/{customerId}")
//    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
//        throw new UnsupportedOperationException();
//    }
}
