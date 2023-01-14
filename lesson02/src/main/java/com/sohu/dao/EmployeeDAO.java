package com.sohu.dao;

import com.sohu.model.EmployeeVO;

import java.util.List;

/**
 * 数据接入层:负责与基础数据库存储进行交互
 */
public interface EmployeeDAO {
    public List<EmployeeVO> getAllEmployees();
}
