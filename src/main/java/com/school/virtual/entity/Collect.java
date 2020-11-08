package com.school.virtual.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Collect {
    @TableId(type = IdType.AUTO)
    private Integer collectId;
    private Integer noticeId;
    private String title;
    private Integer userId;
    private String username;
    private Integer collected;//1表示收藏，0表示取消收藏
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
