package com.jwang261.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jwang261.pojo.User;
import org.springframework.stereotype.Repository;

@Repository // 代表持久层
public interface UserMapper extends BaseMapper<User> {
    // 所有的CRUD编写完成

}
