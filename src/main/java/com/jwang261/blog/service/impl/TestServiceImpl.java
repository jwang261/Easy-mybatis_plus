package com.jwang261.blog.service.impl;

import com.jwang261.blog.pojo.Test;
import com.jwang261.blog.mapper.TestMapper;
import com.jwang261.blog.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jwang261
 * @since 2020-07-25
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}
