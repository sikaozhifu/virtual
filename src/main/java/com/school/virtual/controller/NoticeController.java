package com.school.virtual.controller;

import com.school.virtual.entity.Notice;
import com.school.virtual.service.NoticeService;
import com.school.virtual.vo.DataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"","/"})
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = {"/index"})
    public String to_index(){
        return "index";
    }
    @RequestMapping(value = {"","/","notice_list"})
    public String get_dataVo(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "6") Integer limit){
        DataVo<Notice> dataVo = noticeService.getDataVo(page, limit);
        request.setAttribute("dataVo", dataVo);
        return "forward:/index";
    }

    @RequestMapping(value = "/notice/{id}")
    public String get_notice_by_id(HttpServletRequest request,@PathVariable("id") Integer id){
        Notice notice = noticeService.getNoticeById(id);
        request.setAttribute("notice", notice);
        return "notice_detail";
    }
}
