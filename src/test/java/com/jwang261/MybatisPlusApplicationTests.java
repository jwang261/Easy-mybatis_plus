package com.jwang261;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jwang261.mapper.UserMapper;
import com.jwang261.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    void contextLoads() {
        //参数Wrapper -> 条件构造器 -> 不用就null
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //测试插入
    @Test
    public void testInsert(){
        User user = new User();
        user.setAge(10);
        user.setEmail("phe104@syr.edu");
        user.setName("数组王");

        int res = userMapper.insert(user); //帮我们把id自动生成好了
        System.out.println(res);


    }

    //测试更新
    @Test
    public void testUpdate(){
        User user = new User();
        user.setName("递归王");
        user.setId(5L);
        user.setAge(8);

        int res = userMapper.updateById(user);
        System.out.println(res);

    }

    // 测试乐观锁成功
    @Test
    public void testOptimisticLocker(){
        // 1、查询
        User user = userMapper.selectById(1L);
        // 2、修改
        user.setName("lbw1");
        user.setEmail("sdsdsdsdsd2eddsdwd@adsd.com");
        userMapper.updateById(user);
    }



    // 测试乐观锁失败 -> 多线程下
    @Test
    public void testOptimisticLocker2(){

        User user = userMapper.selectById(1L);
        user.setName("lbw1");
        user.setEmail("sdsdsdsdsd2eddsdwd@adsd.com");


        User user2 = userMapper.selectById(1L);
        user2.setName("lbw2");
        user2.setEmail("dsdwd@adsd.com");

        //模拟另一个线程执行了插队操作
        userMapper.updateById(user2);

        //失败后可以使用自旋锁多次尝试提交
        userMapper.updateById(user);
    }

    //条件查询 map
    @Test
    public void testSelectByBatchIds(){
        HashMap<String, Object> map = new HashMap<>();
        //自定义查询
        map.put("name", "链表王");
        userMapper.selectByMap(map);

    }

    @Test
    public void testPage(){
        //参数1：当前页
        //参数2：页面大小
        Page<User> page = new Page<>(2,5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);

    }

    //测试删除
    @Test
    public void testDelete(){
        userMapper.deleteById(1L);

    }

    //批量删除
    public void testDeleteBatchId(){
        userMapper.deleteBatchIds(Arrays.asList(1,2,3));
    }

    //通过map删除
    public void testDeleteMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","lbw1");
        userMapper.deleteByMap(map);
    }




}
