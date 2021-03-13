package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.model.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.model.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.utility.DtoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());

        List<Employee> employees = new ArrayList<>();
        for(Long emplyeeId : scheduleDTO.getEmployeeIds()){
            employees.add(employeeService.findEmpleyee(emplyeeId));
        }

        List<Pet> pets = new ArrayList<>();
        for(Long petId: scheduleDTO.getPetIds()){
            pets.add(petService.findPetById(petId));
        }

        schedule.setEmployees(employees);
        schedule.setPets(pets);

        return DtoUtility.convertToScheduleDTO(scheduleService.save(schedule));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();

        List<Schedule> schedules = scheduleService.findAll();
        for(Schedule schedule: schedules){
            scheduleDTOs.add(DtoUtility.convertToScheduleDTO(schedule));
        }

        return scheduleDTOs;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);

        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        for(Schedule schedule:schedules){
            scheduleDTOs.add(DtoUtility.convertToScheduleDTO(schedule));
        }

        return scheduleDTOs;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getSchedulesForEmployee(employeeId);

        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        for(Schedule schedule:schedules){
            scheduleDTOs.add(DtoUtility.convertToScheduleDTO(schedule));
        }

        return scheduleDTOs;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getSchedulesForCustomer(customerId);

        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        for(Schedule schedule:schedules){
            scheduleDTOs.add(DtoUtility.convertToScheduleDTO(schedule));
        }

        return scheduleDTOs;
    }
}
