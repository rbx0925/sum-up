package com.rbx;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.jwt.JWTUtil;
import com.rbx.entity.User;
import com.rbx.mapper.MenuMapper;
import com.rbx.mapper.UserMapper;
import com.rbx.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rbx
 * @title
 * @Create 2023-09-08 10:33
 * @Description
 */
@SpringBootTest
public class mapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);

    }

    @Test
    public void testBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("1234");
        System.out.println("encode = " + encode);
        String encode2 = passwordEncoder.encode("1234");
        System.out.println("encode2 = " + encode2);

        boolean matches = passwordEncoder.matches("1234", "$2a$10$gR.k3Q23Nmp1Ok777wqfq.3Ie9UUvFaMxImHf/7r/X6YmpS9/tAra");
        System.out.println("matches = " + matches);
    }

    @Test
    public void testHuToolJwt(){
        String token = JWTUtil.createToken(BeanUtil.beanToMap(new User()), "1234".getBytes());
        System.out.println("token = " + token);
        boolean verify = JWTUtil.verify(token, "1234".getBytes());
        System.out.println("verify = " + verify);
    }

    @Test
    void testInsert2() {
        // 总耗时：95864 ms, 约一分钟30秒左右
        long start = System.currentTimeMillis();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            User user = new User();
            user.setUserName("犬小哈" + i);
            user.setPassword("password"+i);
            user.setPhonenumber("+10086p"+i);
            users.add(user);
        }
        userService.saveBatch(users,1000);
        System.out.println(String.format("总耗时：%s ms", System.currentTimeMillis() - start));
    }

    @Test
    public void testSelectPermsByUserId(){
        List<String> strings = menuMapper.selectPermsByUserId(2L);
        System.out.println("strings = " + strings);
    }
}
