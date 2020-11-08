package com.school.virtual.service.impl;

import com.school.virtual.entity.Notice;
import com.school.virtual.mapper.NoticeMapper;
import com.school.virtual.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeServiceImplTest {

    @Autowired
    private NoticeService noticeService;

    @Test
    void save(){
        Notice notice = new Notice();
        notice.setTitle("test");
        notice.setContent("这是一条测试");
        notice.setAuthor("tom");
        noticeService.save(notice);
    }
}