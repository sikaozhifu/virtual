package com.school.virtual.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    @TableId(type = IdType.AUTO)
    private Integer noticeId;
    private String title;
    private String content;
    private String author;
    private String cover;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic
    private Integer deleted;
}
