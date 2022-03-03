package com.opensource.userservice.common.utils.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
//        // 定义 mysql 数据库基础信息
//        String url = "jdbc:mysql://49.235.255.193:3306/login_center";
//        String username = "test";
//        String password = "88888888";
//
//        // 获取当前的用户目录并添加到全局配置中
//        String path = System.getProperty("user.dir");
//        System.out.println(path);
//        FastAutoGenerator.create(url, username, password)
//                .globalConfig(builder -> {
//                    builder.author("风君子") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
//                            .disableOpenDir() // 禁止生成后自动打开输出目录;
//                            .commentDate("yyyy-MM-dd HH:mm:ss")
//                            .outputDir(path + "/user-service/src/main/java"); // 指定输出目录
//
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.opensource.userservice") // 设置父包名
//                            .moduleName("sys") // 设置父包模块名
//                            .entity("entity")
//                            .service("service")
//                            .serviceImpl("serviceImpl")
//                            .controller("controller")
//                            .mapper("mapper")
//                            .xml("mapper")
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, path + "/user-service/src/main/resources/Mapper/")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder
//                    .addInclude("users")
//                            .serviceBuilder()
//                            .formatServiceFileName("%sService")
//                            .formatServiceImplFileName("%sServiceImpl")
//                            .entityBuilder()
//                            .enableLombok()
//                            .logicDeleteColumnName("deleted")
//                            .controllerBuilder()
//                            .formatFileName("%sController")
//                            .enableRestStyle()
//                            .mapperBuilder()
//                            .superClass(BaseMapper.class)
//                            .formatMapperFileName("%sMapper")
//                            .enableMapperAnnotation()
//                            .formatXmlFileName("%sMapper")
//                            .entityBuilder()
//                            .addTableFills(new Column("create_time", FieldFill.INSERT))
//                            .addTableFills(new Property("updateTime", FieldFill.UPDATE))
//                            .naming(NamingStrategy.underline_to_camel)
//                            .columnNaming(NamingStrategy.underline_to_camel)
//                            .build();
//
//                })
//                .templateConfig(builder -> {
//                    builder.service("/templates/service.java.vm")
//                            .entity("/templates/entity.java.vm")
//                            .serviceImpl("/templates/serviceImpl.java.vm")
//                            .mapper("/templates/mapper.java.vm")
//                            .mapperXml("/templates/mapper.xml.vm")
//                            .controller("/templates/controller.java.vm")
//                            .build();
//                }) // 使用自定义模板
//                .execute();
    }
}
