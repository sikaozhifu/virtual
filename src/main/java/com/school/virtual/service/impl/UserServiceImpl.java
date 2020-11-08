package com.school.virtual.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.virtual.entity.User;
import com.school.virtual.mapper.UserMapper;
import com.school.virtual.service.UserService;
import com.school.virtual.vo.DataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public DataVo<User> getDataVo(Integer page, Integer limit) {
        IPage<User> userIPage = new Page<>(page,limit);
        IPage<User> result = userMapper.selectPage(userIPage, null);
        List<User> userList = result.getRecords();
        DataVo<User> dataVo = new DataVo<>();
        dataVo.setCode(0);
        dataVo.setMsg("");
        dataVo.setCount(result.getTotal());
        dataVo.setData(userList);
        return dataVo;
    }

    @Override
    public int save(User user) {
        User u = getUserByUsername(user.getUsername());
        if (u != null){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", u.getUserId());
            User updateUser = new User();
            if (user.getName() != null){//姓名
                updateUser.setName(user.getName());
            }
            if (user.getPassword() != null){//密码
                updateUser.setPassword(user.getPassword());
            }
            if (user.getSex() != null){//性别
                updateUser.setSex(user.getSex());
            }
            if (user.getPhone() != null){//电话
                updateUser.setPhone(user.getPhone());
            }
            return userMapper.update(updateUser,queryWrapper);
        }
        return userMapper.insert(user);
    }

    @Override
    public User getUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User getUserByUserId(Integer userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public User getUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(user);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public int delete(Integer userId) {
        return userMapper.deleteById(userId);
    }

}
