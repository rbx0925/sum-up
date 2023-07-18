package com.atguigu.controller;

import com.atguigu.bean.Employee;
import com.atguigu.service.impl.DepartmentServiceImpl;
import com.atguigu.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.EmployeeController
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;
    @Autowired
    DepartmentServiceImpl departmentService;

    //不设置value属性，当直接访问/employee时到展示所有员工信息的页面
    @RequestMapping
    public String toShowPage(Map map) {
        map.put("employees", employeeService.showAllEmp());
        return "employee/show";
    }

    //跳转到创建页面-合并后被淘汰！
//    @RequestMapping(
//            value = "/toCreatePage",
//            method = RequestMethod.GET
//    )
//    public String toCreatePage(Map map) {
//        map.put("departments", departmentService.showAllDept());
//        return "employee/create";
//    }

    //跳转到修改页面-合并后被淘汰！
//    @RequestMapping(
//            value = "/toEditPage/{id}",
//            method = RequestMethod.GET
//    )
//    public String toEditPage(
//            @PathVariable Integer id,
//            Map map
//    ) {
//        map.put("employee", employeeService.showEmp(id));
//        map.put("departments", departmentService.showAllDept());
//        return "employee/edit";
//    }

    //跳转到创建页面或修改页面-合并
    @RequestMapping(
            value = {"/toEditPage/{id}", "/toCreatePage/{id}"},
            method = RequestMethod.GET
    )
    public String toCreateOrEditPage(
            @PathVariable Integer id,
            Map map
    ) {
        Employee employee;

        //当id为0时去新增页面，否则去修改页面
        if (id == 0) {
            employee = new Employee(null, null, null, null, null);
        } else {
            employee = employeeService.showEmp(id);
        }

        //向请求域添加信息
        map.put("employee", employee);
        map.put("departments", departmentService.showAllDept());

        return "employee/editOrCreate";
    }

    //创建对象-合并后被淘汰！
//    @RequestMapping(
//            value = "/create",
//            method = RequestMethod.PUT
//    )
//    public String create(Employee employee) {
//        employeeService.createEmp(employee);
//        return "redirect:/employee";
//    }

    //创建或修改对象-合并了两个操作！
    @RequestMapping(
            value = "/edit",
            method = RequestMethod.POST
    )
    public String edit(Employee employee) {
        employeeService.editEmp(employee);
        return "redirect:/employee";
    }

    //通过id删除对象
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.DELETE
    )
    public String delete(@PathVariable Integer id) {
        employeeService.deleteEmp(id);
        return "redirect:/employee";
    }

}
