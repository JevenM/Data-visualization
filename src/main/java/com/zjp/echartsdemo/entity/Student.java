package com.zjp.echartsdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "stu_score")
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @TableId(value = "sid", type = IdType.AUTO)
    private Long sid;
    private String sname;
    private String card;
    private Integer politics;
    private Integer english;
    private Integer math;
    private Integer computer;
}
