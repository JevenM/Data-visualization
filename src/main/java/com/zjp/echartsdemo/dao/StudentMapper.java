package com.zjp.echartsdemo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjp.echartsdemo.entity.Student;

@Repository
@Transactional
public interface StudentMapper extends BaseMapper<Student> {
    // 所有的CRUD操作都已经编写好了
}
