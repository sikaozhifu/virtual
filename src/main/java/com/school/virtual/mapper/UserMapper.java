package com.school.virtual.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.virtual.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
