package com.jwang261;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jwang261.mapper.UserMapper;
import com.jwang261.pojo.User;
import com.mysql.cj.QueryResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    void contextLoads() {
        //查询name和邮箱都不为null的用户，以及年龄大于等于18
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age",18);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test2(){
        //名字 = 链表网的查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","链表王");
        System.out.println(userMapper.selectOne(wrapper));

    }

    @Test
    void test3(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("age",10);
        userMapper.selectCount(wrapper);

    }
    @Test
    void test4(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",10,19);
        userMapper.selectCount(wrapper);

    }

    //模糊查询
    @Test
    void test5(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .notLike("name","e")
                .likeRight("email","phe"); // xxx%

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void test6(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //id 在子查询中查出来
        wrapper.inSql("id","select id from user where id < 3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);


    }
    @Test
    void test7(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过id进行排序
        wrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);

    }
}
