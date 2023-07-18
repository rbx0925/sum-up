package com.atguigu.service.impl;

import com.atguigu.bean.Employee;
import com.atguigu.dao.EmployeeDao;
import com.atguigu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.EmployeeService
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDao employeeDao;

    //展示所有员工对象
    @Override
    public Collection<Employee> showAllEmp() {
        return employeeDao.getAll();
    }

    //展示一个员工对象
    @Override
    public Employee showEmp(Integer id) {
        return employeeDao.get(id);
    }

    //创建一个员工对象
    @Override
    public void createEmp(Employee employee) {
        employeeDao.saveOrUpdate(employee);
    }

    //基于id删除一个员工对象
    @Override
    public void deleteEmp(Integer id){
        employeeDao.delete(id);
    }

    //修改一个员工对象
    @Override
    public void editEmp(Employee employee) {
        employeeDao.saveOrUpdate(employee);
    }

}
