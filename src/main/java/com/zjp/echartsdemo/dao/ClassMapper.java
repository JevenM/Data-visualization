package com.zjp.echartsdemo.dao;

import org.apache.ibatis.annotations.Mapper;
import com.zjp.echartsdemo.entity.Class;
import java.util.List;

@Mapper
public interface ClassMapper {
    List<Class> findAll();
}
