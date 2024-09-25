package com.empolyees.springboot.mvcdemo.service;

import com.empolyees.springboot.mvcdemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    List<Employee> findAllByOrderByLastNameAsc();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);

}
