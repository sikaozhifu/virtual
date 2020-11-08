package com.school.virtual.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.virtual.entity.Notice;
import com.school.virtual.mapper.NoticeMapper;
import com.school.virtual.service.NoticeService;
import com.school.virtual.vo.DataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public int save(Notice notice) {
        Integer noticeId = notice.getNoticeId();
        if (noticeId != null){
            return noticeMapper.updateById(notice);
        }
        return noticeMapper.insert(notice);
    }

    @Override
    public DataVo<Notice> getDataVo(Integer page, Integer limit) {
        IPage<Notice> noticeIPage = new Page<>(page,limit);
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");//按照时间降序
        IPage<Notice> result = noticeMapper.selectPage(noticeIPage, wrapper);
        List<Notice> noticeList = result.getRecords();
        DataVo<Notice> dataVo = new DataVo<>();
        dataVo.setCode(0);
        dataVo.setMsg("");
        dataVo.setCount(result.getTotal());
        dataVo.setCurr(result.getCurrent());
        dataVo.setData(noticeList);
        return dataVo;
    }

    @Override
    public int delete(Integer noticeId) {
        return noticeMapper.deleteById(noticeId);
    }

    @Override
    public Notice getNoticeById(Integer noticeId) {
        return noticeMapper.selectById(noticeId);
    }
}
