package com.fjc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : fanjic 
 * @date : 2021/02/02 01:15:57
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "")
public class Person implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id" ,example = "")

    private Integer id;

    /**
     * 名字
     */
    @ApiModelProperty(value = "名字" ,example = "")
    private String name;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别" ,example = "")
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄" ,example = "")
    private Byte age;

    /**
     * 技能
     */
    @ApiModelProperty(value = "技能" ,example = "")
    private String skill;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间" ,example = "")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间" ,example = "")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}