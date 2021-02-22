package com.fjc.controller;

import com.fjc.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("person")
public class PersonController {
    @Resource
    PersonMapper personMapper;

    @RequestMapping("/test")
    public String test(){
        into("无");
        return "index";
    }

    @RequestMapping("/into")
    public String into(@RequestParam(value = "val",required = false)String val){
        System.out.println("hello");
        List pers=personMapper.findSome(val);
        pers.forEach(System.out::println);
        return "index";
    }
}
