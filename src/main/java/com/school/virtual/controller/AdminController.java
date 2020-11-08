package com.school.virtual.controller;

import com.school.virtual.entity.Admin;
import com.school.virtual.entity.Notice;
import com.school.virtual.entity.User;
import com.school.virtual.service.AdminService;
import com.school.virtual.service.FileService;
import com.school.virtual.service.NoticeService;
import com.school.virtual.service.UserService;
import com.school.virtual.utils.MD5Util;
import com.school.virtual.utils.UploadUtils;
import com.school.virtual.vo.DataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = {"/",""})
    public String to_login(){
        return "admin_login";
    }

    @RequestMapping(value = {"/index"})
    public String to_admin(HttpSession session){
        if (session.getAttribute("admin") == null){
            return "redirect:";
        }
        return "admin";
    }
    @RequestMapping("/table")
    public String to_table(){
        return "table";
    }

    @RequestMapping("/edit_notice")
    public String to_edit_notice(){
        return "edit_notice";
    }
    @RequestMapping("/notice_list")
    public String to_notice_list(){
        return "notice_list";
    }

    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    @ResponseBody
    public DataVo<Admin> login(HttpSession session,@RequestParam String username,@RequestParam String password){
        DataVo<Admin> dataVo = new DataVo<>();
        Admin admin = new Admin();
        admin.setAdminUsername(username);
        admin.setAdminPassword(MD5Util.md5(password));
        if(adminService.getAdmin(admin) != null){
            //登录成功
            session.setAttribute("admin", adminService.getAdmin(admin));
            dataVo.setMsg("登录成功");
            dataVo.setCode(0);
            return dataVo;
        }
        dataVo.setMsg("登录失败");
        dataVo.setCode(1);
        return dataVo;
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        if (session.getAttribute("admin") != null){
            session.removeAttribute("admin");
        }
        return "redirect:";
    }

    @RequestMapping("/users")
    @ResponseBody
    public DataVo<User> getUserList(Integer page,Integer limit){
        return userService.getDataVo(page,limit);
    }

    @RequestMapping("/notices")
    @ResponseBody
    public DataVo<Notice> getNoticeList(Integer page,Integer limit){
        return noticeService.getDataVo(page,limit);
    }

    @RequestMapping(value = "/upload_img", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> upload_img(@RequestParam MultipartFile file){
        Map<String,Object> result = new HashMap<>();//返回结果
        Map<String, String> data = new HashMap<>();//存放图片的路径和名字
        String file_extension =  file.getOriginalFilename().substring( file.getOriginalFilename().indexOf("."));//得到后缀名
        String image_name= UUID.randomUUID().toString().replace("-", "")  + file_extension;//生成一个UUID为图片命名
        File file_dir = UploadUtils.getImgDirFile();//存放上传图片的文件夹
        String image_path =file_dir.getAbsolutePath() + File.separator + image_name;
        boolean is_save = fileService.saveFile(file, image_path);
        if (is_save){
            data.put("src", "/upload/images/" + image_name);//返回一个地址，这个地址不是文件的储存地址，而是前端访问地址
            data.put("title",file.getOriginalFilename());//原始文件名
            result.put("code", 0);
            result.put("msg", "上传成功");
            result.put("data", data);
            System.out.println("/upload/images/" + image_name);
        }else {
            result.put("code", 1);
            result.put("msg", "上传失败");
        }
        return result;
    }

    @RequestMapping(value = "/saveNotice",method = RequestMethod.POST)
    @ResponseBody
    public int saveNotice(Notice notice){
        return noticeService.save(notice);
    }

    @RequestMapping(value = "/deleteNotice",method = RequestMethod.POST)
    @ResponseBody
    public int deleteNotice(Integer noticeId){
        return noticeService.delete(noticeId);
    }

    @RequestMapping(value = "/updateNotice/{noticeId}",method = RequestMethod.GET)
    public String updateNoticeById(@PathVariable Integer noticeId,HttpServletRequest request){
        Notice notice =  noticeService.getNoticeById(noticeId);
        request.setAttribute("notice", notice);
        return "update_notice";
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public int deleteUser(Integer userId){
        return userService.delete(userId);
    }

    @RequestMapping(value = "/updateUser/{userId}",method = RequestMethod.GET)
    public String updateUserById(@PathVariable Integer userId,HttpServletRequest request){
        User user = userService.getUserByUserId(userId);
        request.setAttribute("user", user);
        return "update_user";
    }

    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    @ResponseBody
    public int saveUser(User user){
        User u = userService.getUserByUserId(user.getUserId());
        user.setPassword(u.getPassword());
        return userService.save(user);
    }
}
