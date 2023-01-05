package com.zjp.echartsdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjp.echartsdemo.entity.Student;
import com.zjp.echartsdemo.dao.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/suda")
public class SearchController {
    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/gologin")
    public String home() {
        return "login_suda";
    }

    @PostMapping(value = "/login", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String, String> login(HttpServletRequest request) {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String card = request.getParameter("password");
        // query equal
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper
                .eq("sid", id)
                .eq("sname", username)
                .eq("card", card);
        Integer count = studentMapper.selectCount(wrapper);
        // System.out.println("the count is " + count);

        HashMap<String, String> map = new HashMap<>();
        if (count == 1) {
            // 登录验证成功，通过id查询该考生的成绩（id具有唯一性）
            map.put("result", id);
            map.put("code", "100");
        } else {
            map.put("result", "登录失败！输入信息有误！");
            map.put("code", "200");
        }
        return map;
    }

    @GetMapping("/searchById/{id}")
    public ModelAndView searchById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("person");
        Student student = studentMapper.selectById(id);
        System.out.println(student);
        Integer total = student.getPolitics() + student.getEnglish() + student.getMath() + student.getComputer();
        modelAndView.addObject("student", student);
        modelAndView.addObject("totalScore", total);
        return modelAndView;
    }
}
