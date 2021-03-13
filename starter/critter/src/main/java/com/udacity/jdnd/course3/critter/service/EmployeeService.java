package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.model.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.model.dto.EmployeeSkill;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findEmpleyee(long id){
        return employeeRepository.getOne(id);
    }

    public Employee setAvailability(Set<DayOfWeek> daysAvailable, long employeeId){
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        return employeeRepository.save(employee);
    }

    public List<Employee> findEmployeeForService(EmployeeRequestDTO requestDTO ){
        DayOfWeek dayAvailable = requestDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> requestSkills = requestDTO.getSkills();
        List<Employee> employees = employeeRepository.getAllByDaysAvailableContains(dayAvailable);

        List<Employee> resultEmployeeSet = new ArrayList<>();
        for(Employee employee: employees){
            if(employee.getSkills().containsAll(requestSkills)){
                resultEmployeeSet.add(employee);
            }
        }

        return resultEmployeeSet;
    }

}
