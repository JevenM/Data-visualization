package com.zjp.echartsdemo.service;

import java.util.List;
import com.zjp.echartsdemo.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjp.echartsdemo.dao.ClassMapper;

@Service
public class ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Transactional  
    public List<Class> findAll() {
        return classMapper.findAll();
    }
}
