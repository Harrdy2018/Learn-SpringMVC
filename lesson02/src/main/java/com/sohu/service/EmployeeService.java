package com.sohu.service;

import com.sohu.model.EmployeeVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务层:负责与DAO层进行交互
 */
public interface EmployeeService {
    public List<EmployeeVO> getAllEmployees();
}
