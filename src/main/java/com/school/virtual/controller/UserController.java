package com.school.virtual.controller;

import com.school.virtual.entity.User;
import com.school.virtual.service.UserService;
import com.school.virtual.utils.MD5Util;
import com.school.virtual.vo.DataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String to_login(){
        return "login";
    }
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public DataVo<User> login(HttpSession session, @RequestParam String username, @RequestParam String password){
        DataVo<User> dataVo = new DataVo<>();
        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Util.md5(password));
        if (userService.getUser(user) != null){
            session.setAttribute("user", userService.getUser(user));
            dataVo.setMsg("登录成功！");
            dataVo.setCode(0);
            return dataVo;
        }
        dataVo.setCode(1);
        dataVo.setMsg("登录失败！");
        return dataVo;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        if (session.getAttribute("user") != null){
            session.removeAttribute("user");
        }
        return "redirect:";
    }

    @RequestMapping("/home")
    public String detail(HttpSession session){
        if (session.getAttribute("user") == null){
            return "redirect:";
        }
        return "home";
    }

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    @ResponseBody
    public int register(@RequestParam String username,@RequestParam String password,@RequestParam String sex){
        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Util.md5(password));
        user.setSex(sex);
        return userService.save(user);
    }
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    @ResponseBody
    public int update(HttpSession session,
                      @RequestParam String username,
                      @RequestParam String name,
                      @RequestParam String sex,
                      @RequestParam String phone){
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setSex(sex);
        user.setPhone(phone);
        if(userService.save(user) == 1){
            //修改成功
            session.setAttribute("user", userService.getUser(user));
            return 1;
        }
        return 0;
    }

    @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public int updatePassword(HttpSession session,
                      @RequestParam String username,
                      @RequestParam String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Util.md5(password));
        if(userService.save(user) == 1){
            //修改成功
            session.setAttribute("user", userService.getUser(user));
            return 1;
        }
        return 0;
    }
}
