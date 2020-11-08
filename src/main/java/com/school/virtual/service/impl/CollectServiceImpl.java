package com.school.virtual.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.virtual.entity.Collect;
import com.school.virtual.mapper.CollectMapper;
import com.school.virtual.service.CollectService;
import com.school.virtual.vo.DataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;
    @Override
    public Collect getCollect(Integer userId, Integer noticeId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("notice_id", noticeId);
        return collectMapper.selectOne(queryWrapper);
    }

    @Override
    public int save(Collect collect) {
        Collect c = getCollect(collect.getUserId(), collect.getNoticeId());
        if (c != null){
            collect.setCollectId(c.getCollectId());
            return collectMapper.updateById(collect);
        }
        return collectMapper.insert(collect);
    }

    @Override
    public List<Collect> getListByUserId(Integer userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        return collectMapper.selectList(queryWrapper);
    }

    @Override
    public DataVo<Collect> getDataVo(Integer userId, Integer page, Integer limit) {
        IPage<Collect> collectIPage = new Page<>(page, limit);
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.orderByDesc("update_time");//按照时间降序
        IPage<Collect> result = collectMapper.selectPage(collectIPage, queryWrapper);
        DataVo<Collect> dataVo = new DataVo<>();
        dataVo.setData(result.getRecords());
        dataVo.setMsg("查询成功");
        dataVo.setCode(0);
        dataVo.setCurr(result.getCurrent());
        dataVo.setCount(result.getTotal());
        return dataVo;
    }
}
