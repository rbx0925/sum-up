package com.atguigu.service;

import com.atguigu.bean.Department;
import com.atguigu.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.DepartmentService
 */
public interface DepartmentService {

    //展示所有部门信息
    Collection<Department> showAllDept();

}
