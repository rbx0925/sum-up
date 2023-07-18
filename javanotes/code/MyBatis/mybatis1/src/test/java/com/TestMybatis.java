package com;

import com.mybatis.bean.Employee;
import com.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.TestMydatis
 */
public class TestMybatis {
    @Test
    public void test01() throws Exception{
        //从类的根路径resources目录下查找配置文件的路径
        String resource = "mybatis-config.xml";
        //SqlSessionFactoryBuilder.build()需要传入配置文件的输入流对象
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //通过SqlSessionFactoryBuilder对象的build方法获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过SqlSessionFactory对象的openSession方法获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过SqlSession得到EmployeeMapper接口的映射对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //执行已映射的sql语句的方法
        List<Employee> list = mapper.getAllEmp();
        System.out.println(list);

    }

}
