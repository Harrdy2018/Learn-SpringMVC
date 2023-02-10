package com.sohu.service;

import com.sohu.bo.EmployeeBO;
import com.sohu.dao.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired(required = true)
    EmployeeDAO employeeDAO;

    @Override
    public List<EmployeeBO> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public EmployeeBO queryEmployeeById(Integer id) {
        return employeeDAO.queryEmployeeById(id);
    }
}
