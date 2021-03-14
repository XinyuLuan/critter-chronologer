package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.repository.CusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CusService {
    @Autowired
    CusRepository cusRepository;

    public List<Customer> findAll(){
        return cusRepository.getAllCustomers();
    }
}
