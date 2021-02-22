package com.fjc.domain;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

/**
 * @ Author : glt
 **/
public class CommentGenerator extends DefaultCommentGenerator {

    private boolean addRemarkComments = false;
    private static final String EXAMPLE_SUFFIX = "Example";
    private static final String API_MODEL_PROPERTY_FULL_CLASS_NAME ="io.swagger.annotations.ApiModelProperty";

    /**
     * 设置用户配置的参数
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.addRemarkComments= StringUtility.isTrue(properties.getProperty("addRemarkComments"));
    }

    /**
     * 给字段添加注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks=introspectedColumn.getRemarks();
        String jdbcTypeName = introspectedColumn.getJdbcTypeName();
        if(addRemarkComments&&StringUtility.stringHasValue(remarks)){
            addFieldJavaDoc(field, remarks);
            //数据库中特殊字符需要转义
            if(remarks.contains("\"")){
                remarks=remarks.replace("\"","'");
            }

            //todo 给model的字段添加swagger注解 swagger二选一  格式例: @ApiModelProperty(value = "确认收货时间" ,example = "")
            field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\" ,example = \"\")");

            //todo 给model的字段添加swagger注解 swagger二选一  格式例: @ApiModelProperty(value = "确认收货时间")
            //field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\" )");

            //todo solr注解
            //field.addJavaDocLine("@Field( \""+ field.getName() + "\")");

            //todo 为date类型字段自动添加@JsonFormat 注解
            if(jdbcTypeName.equals("TIMESTAMP")||jdbcTypeName.equals("TIME")){
                field.addJavaDocLine("@JsonFormat(locale=\"zh\", timezone=\"GMT+8\", pattern=\"yyyy-MM-dd HH:mm:ss\")");
            }
        }
        super.addFieldComment(field, introspectedTable, introspectedColumn);
    }

    /**
     * todo 给model的字段添加注释 例://订单id 注释格式二选一
     */
    private void addFieldJavaDoc(Field field, String remarks) {
        //todo 给model的字段添加注释 例://订单id
//        String[] remarkLines=remarks.split(System.getProperty("line.separator"));//换行
//        field.addJavaDocLine("//"+remarkLines[0]);

        //todo 给model的字段添加注释 例: /**
        //todo                           *  订单id
        //todo                           */
        field.addJavaDocLine("/**");
        String[] remarkLines=remarks.split(System.getProperty("line.separator"));//换行
        field.addJavaDocLine(" * "+remarkLines[0]);
        field.addJavaDocLine(" */");
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        super.addJavaFileComment(compilationUnit);
        if(compilationUnit.isJavaInterface()&&compilationUnit.
                getType().getFullyQualifiedName().contains(EXAMPLE_SUFFIX)){
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_CLASS_NAME));
        }
    }

}
