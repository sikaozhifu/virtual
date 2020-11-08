package com.school.virtual.service;

import com.school.virtual.entity.User;
import com.school.virtual.vo.DataVo;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public DataVo<User> getDataVo(Integer page, Integer limit);
    public int save(User user);
    public User getUserByUsername(String username);
    User getUserByUserId(Integer userId);
    public User getUser(User user);
    int delete(Integer userId);
}
