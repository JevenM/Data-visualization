package com.zjp.echartsdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjp.echartsdemo.dao.ProvinceMapper;
import com.zjp.echartsdemo.entity.Province;
import com.zjp.echartsdemo.service.IprovinceService;

@Service
public class IprovinceImpl implements IprovinceService {
    @Autowired
    ProvinceMapper pMapper;

    @Override
    @Transactional
    public List<Province> selectAll() {
        return pMapper.selectAll();
    }
}
