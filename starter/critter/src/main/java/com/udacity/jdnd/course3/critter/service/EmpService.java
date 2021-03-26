package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.model.dto.EmployeeSkill;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmpService {
    @Autowired
    EmpRepository empRepository;

    public List<Employee> findAllEmployeeByIds(List<Long> ids){
        return empRepository.getEmployeesByIds(ids);
    }

    public Employee saveEmployee(Employee employee){
        return empRepository.save(employee);
    }

    public List<Employee> findEmployeeForService(EmployeeRequestDTO requestDTO ){
        DayOfWeek dayAvailable = requestDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> requestSkills = requestDTO.getSkills();
        List<Employee> employees = empRepository.getAllByAvailableDays(dayAvailable);

        List<Employee> resultEmployeeSet = new ArrayList<>();
        for(Employee employee: employees){
            if(employee.getSkills().containsAll(requestSkills)){
                resultEmployeeSet.add(employee);
            }
        }

        return resultEmployeeSet;
    }

}
