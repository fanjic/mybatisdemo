package com.fjc.mapper;

import com.fjc.entity.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : fanjic 
 * @date : 2021/02/02 01:15:57
 **/
@Mapper
public interface PersonMapper {


    List findSome(String val);

    List<Person> getAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}