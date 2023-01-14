package com.sohu;

import com.sohu.bo.EmployeeBO;
import com.sohu.po.EmployeePO;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import util.BeanConvertUtils;

import java.util.ArrayList;
import java.util.List;

public class example {
    @Test
    public void test1(){
        // PO转BO 浅拷贝
        EmployeePO po = new EmployeePO();
        po.setId(1);
        po.setFirstName("Lokesh");
        po.setLastName("Gupta");

        EmployeeBO bo = new EmployeeBO();
        BeanUtils.copyProperties(po, bo);
        System.out.println(bo);
    }

    @Test
    public void test2(){
        // PO转BO 通过工具类转换
        EmployeePO po = new EmployeePO();
        po.setId(1);
        po.setFirstName("Lokesh");
        po.setLastName("Gupta");

        EmployeeBO bo = BeanConvertUtils.convertTo(po, EmployeeBO::new);
        System.out.println(bo);
    }

    @Test
    public void test3(){
        // PO转BO 通过工具类转换 通过lambda表达式特殊处理个别字段
        EmployeePO po = new EmployeePO();
        po.setId(1);
        po.setFirstName("Lokesh");
        po.setLastName("Gupta");

        EmployeeBO bo = BeanConvertUtils.convertTo(po, EmployeeBO::new, (source, target)->{
            target.setLastName(source.getLastName()+" 处理过");
        });
        System.out.println(bo);
    }

    @Test
    public void test4(){
        // PO转BO 通过工具类转换List
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

        List<EmployeeBO> employeeBOS= BeanConvertUtils.convertListTo(employeePOS, EmployeeBO::new);
        System.out.println(employeeBOS);
    }

    @Test
    public void test5(){
        // PO转BO 通过工具类转换List 通过lambda表达式特殊处理个别字段
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

        List<EmployeeBO> employeeBOS= BeanConvertUtils.convertListTo(employeePOS, EmployeeBO::new, (source, target)->{
            target.setLastName(source.getLastName()+" 处理过");
        });
        System.out.println(employeeBOS);
    }
}
