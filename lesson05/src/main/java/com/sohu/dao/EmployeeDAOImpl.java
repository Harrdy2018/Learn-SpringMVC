package com.sohu.dao;

import com.oppo.tool.BeanConvertUtils;
import com.sohu.bo.EmployeeBO;
import com.sohu.po.EmployeePO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    @Override
    public List<EmployeeBO> getAllEmployees() {
        List<EmployeePO> employeePOS = new ArrayList<>();
        EmployeePO po1 = new EmployeePO();
        po1.setId(1);
        po1.setFirstName("Lokesh");
        po1.setLastName("Gupta");
        employeePOS.add(po1);
        EmployeePO po2 = new EmployeePO();
        po2.setId(2);
        po2.setFirstName("Raj");
        po2.setLastName("Kishore");
        employeePOS.add(po2);

        // PO->BO
        List<EmployeeBO> employeeBOS = BeanConvertUtils.convertListTo(employeePOS, EmployeeBO::new);
        return employeeBOS;
    }
}
