package com.huijun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: beetl
 * @description: beetlcontroller类
 * @author: Mr.si
 * @create: 2019-06-03 14:46
 **/
@Controller
@RequestMapping("/beetl")
public class BeetlController {
    @RequestMapping("/a")
    public Object a(Model model){
        Map map = new HashMap<>();
        map.put("name","张三");
        map.put("age",23);
        map.put("sex",'男');
        map.put("score",50);
        model.addAttribute("map",map);
        return "beetl.html";
    }
}
