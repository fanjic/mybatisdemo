package com.fjc;

import com.fjc.entity.Person;
import com.fjc.mapper.PersonMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MybatisdemoApplicationTests {
    @Resource
    PersonMapper personMapper;

    @Test
    void test1() {
        PageHelper.startPage(12, 4);
        List<Person> allPer=personMapper.getAll();
        PageInfo<Person> curPer=new PageInfo<Person>(allPer);
        System.out.println("页数："+curPer.getPages());
        System.out.println("总数："+curPer.getTotal());
        System.out.println("每页的数据量："+curPer.getSize());
        List<Person> pers=curPer.getList();
        pers.forEach(System.out::println);
        System.out.println(curPer);
    }

    @Test
    void test2() {

    }
}
