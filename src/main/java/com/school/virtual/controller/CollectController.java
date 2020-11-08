package com.school.virtual.controller;

import com.school.virtual.entity.Collect;
import com.school.virtual.entity.Notice;
import com.school.virtual.entity.User;
import com.school.virtual.service.CollectService;
import com.school.virtual.service.NoticeService;
import com.school.virtual.service.UserService;
import com.school.virtual.vo.DataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserService userService;

    @Autowired
    private CollectService collectService;

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public int get_collect(@RequestParam Integer userId,
                           @RequestParam Integer noticeId){
        if (userId ==null){
            return 0;
        }
        Collect collect = collectService.getCollect(userId, noticeId);
        if (collect != null){
            return collect.getCollected();
        }
        return 0;
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public int update_collect(@RequestParam Integer userId,
                              @RequestParam Integer noticeId,
                              @RequestParam Integer collected){
        if (userId ==null){
            return 0;
        }
        Notice notice = noticeService.getNoticeById(noticeId);
        User user = userService.getUserByUserId(userId);
        Collect collect = new Collect();
        collect.setUserId(user.getUserId());
        collect.setNoticeId(noticeId);
        collect.setUsername(user.getUsername());
        collect.setTitle(notice.getTitle());
        collect.setCollected(collected);
        return collectService.save(collect);
    }

    @RequestMapping(value = "/getList/{userId}")
    @ResponseBody
    public DataVo<Collect> getCollectList(@PathVariable Integer userId,Integer page,Integer limit){
        return collectService.getDataVo(userId, page, limit);
    }

}
