package com.sohu.service;

import com.sohu.bo.EmployeeBO;
import com.sohu.vo.EmployeeVO;

import java.util.List;

/**
 * 服务层:负责与DAO层进行交互
 */
public interface EmployeeService {
    List<EmployeeBO> getAllEmployees();

    EmployeeBO queryEmployeeById(Integer id);
}
