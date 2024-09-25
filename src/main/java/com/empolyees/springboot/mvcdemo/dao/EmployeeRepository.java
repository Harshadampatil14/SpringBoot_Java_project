package com.empolyees.springboot.mvcdemo.dao;

import com.empolyees.springboot.mvcdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    //Add method to sort by last name
    public List<Employee> findAllByOrderByLastNameAsc();

}
