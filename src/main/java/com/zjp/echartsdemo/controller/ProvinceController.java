package com.zjp.echartsdemo.controller;

import com.zjp.echartsdemo.entity.Product;
import com.zjp.echartsdemo.entity.Province;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjp.echartsdemo.entity.Class;
import com.zjp.echartsdemo.service.ClassService;
import com.zjp.echartsdemo.service.IprovinceService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
// @CrossOrigin(origins = "*",maxAge = 3600)
@Controller
public class ProvinceController {
    @Autowired
    IprovinceService pService;
    @Autowired
    ClassService classService;

    @RequestMapping("/getAllProvince")
    @ResponseBody // 以json格式返回数据
    public List<Province> showData() {
        List<Province> provinceArrayList = new ArrayList<>();
        provinceArrayList = pService.selectAll();
        return provinceArrayList;
    }

    @GetMapping("/class")
    public String gotoClass() {
        return "class";
    }

    @GetMapping("/dating")
    public String gotoDating() {
        return "dating";
    }

    @GetMapping("/json")
    public String gotoJson() {
        return "ceshijson";
    }

    @RequestMapping("/{filename}")
    @ResponseBody
    public JSON json(@PathVariable("filename") String filename) throws Exception {
        // System.out.println(filename);
        Resource resource = new ClassPathResource("static/" + filename);
        File file = resource.getFile();
        Reader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        reader.close();
        String jsonStr = sb.toString();
        // System.out.println(jsonStr);
        if (jsonStr.charAt(0) == '[') {
            JSONArray jsonArr = JSON.parseArray(jsonStr);
            // System.out.println(jsonArr);
            return jsonArr;
        } else {
            JSONObject jsonObj = JSON.parseObject(jsonStr);
            // System.out.println(jsonObj);
            return jsonObj;
        }
    }

    @RequestMapping(value = "/getAllClass", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<Class> showAllClass() {
        return classService.findAll();
    }

    @RequestMapping("/clothes")
    @ResponseBody
    public List<Product> myProject() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductName("袜子");
        product1.setNums(15);
        Product product2 = new Product();
        product2.setProductName("羊毛衫");
        product2.setNums(20);
        Product product3 = new Product();
        product3.setProductName("雪纺衫");
        product3.setNums(24);
        Product product4 = new Product();
        product4.setProductName("高跟鞋");
        product4.setNums(30);

        productArrayList.add(product1);
        productArrayList.add(product2);
        productArrayList.add(product3);
        productArrayList.add(product4);

        return productArrayList;
    }

    @RequestMapping("/getny")
    @ResponseBody
    public Map<String, Object> getData() {
        Map<String, Object> map = new HashMap<>();
        JSONArray list = new JSONArray();
        JSONObject o1 = new JSONObject();
        String name = "name";
        o1.put(name, "大棚数");
        String value = "value";
        o1.put(value, 1310);
        JSONObject o2 = new JSONObject();
        o2.put(name, "仓库");
        o2.put(value, 90);
        JSONObject o3 = new JSONObject();
        o3.put(name, "养殖基地");
        o3.put(value, 415);
        JSONObject o4 = new JSONObject();
        o4.put(name, "销售中心");
        o4.put(value, 317);
        list.add(o1);
        list.add(o2);
        list.add(o3);
        list.add(o4);
        String[] nameList = { "大棚数", "养殖基地", "仓库", "销售中心" };
        map.put("data_", list);
        map.put("names", nameList);
        return map;
    }

    @RequestMapping("/echarts")
    public String myECharts(Model model) {

        model.addAttribute("skirt", "衬衫");
        model.addAttribute("nums", 50);

        return "echarts";
    }

    @RequestMapping("/view")
    public String myView() {
        return "view";
    }

    @RequestMapping("/view1")
    public String myView1() {
        return "view1";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String loginIndex() {
        return "login";
    }

    @RequestMapping("/getlist")
    public String list() {
        return "list1";
    }

    // 一个页面的某个模块更新内容
    @RequestMapping("/list")
    public String list(Model model) {
        List<String> lists = new ArrayList<>();
        lists.add("aaaa");
        lists.add("bbbb");
        lists.add("cccc");
        lists.add("dddd");
        model.addAttribute("list", lists);
        return "list1::list";
    }

    // SpringBoot接收ajax请求的方式
    // 方式一：使用HttpServletRequest request接收请求参数
    @GetMapping("/getCode1")
    @ResponseBody
    public String getCode(HttpServletRequest request) {
        String id = request.getParameter("id");
        System.out.println("AJAX传递的参数：" + id);
        // 获取5位验证码
        return randomCodes();
    }

    // 方式二：用@Param映射单个值
    @GetMapping("/getCode")
    @ResponseBody
    public String getCode1(@Param("id") Integer id) {
        System.out.println(id);
        // 获取5位验证码
        return randomCodes();
    }

    public String randomCodes() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            code.append(ch);
        }
        return code.toString();
    }

}
