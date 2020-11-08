package com.school.virtual.service;

import com.school.virtual.entity.Notice;
import com.school.virtual.vo.DataVo;

public interface NoticeService {
    public int save(Notice notice);

    DataVo<Notice> getDataVo(Integer page, Integer limit);

    int delete(Integer noticeId);
    public Notice getNoticeById(Integer noticeId);
}
