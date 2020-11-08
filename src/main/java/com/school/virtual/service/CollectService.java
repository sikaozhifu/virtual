package com.school.virtual.service;

import com.school.virtual.entity.Collect;
import com.school.virtual.vo.DataVo;

import java.util.List;

public interface CollectService {
    Collect getCollect(Integer userId,Integer noticeId);
    int save(Collect collect);
    List<Collect> getListByUserId(Integer userId);
    DataVo<Collect> getDataVo(Integer userId,Integer page,Integer limit);
}
