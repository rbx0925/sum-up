package com.atguigu.controller;

import com.atguigu.bean.Employee;
import com.atguigu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.EmployeeController
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(
            value = "/index",
            method = RequestMethod.GET
    )
    public String toEmployeePage(Map map){
        map.put("employees",employeeService.getEmpAll());
        return "employee";
    }

}
