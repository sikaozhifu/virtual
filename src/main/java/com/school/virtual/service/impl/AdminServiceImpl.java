package com.school.virtual.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.virtual.entity.Admin;
import com.school.virtual.mapper.AdminMapper;
import com.school.virtual.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdmin(Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(admin);
        return adminMapper.selectOne(queryWrapper);
    }
}
