package com.itrhh.module.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;
import java.util.Collections;

/**
 * @Classname CategoryGeneratior
 * @Description TODO
 * @Created by 14195
 * @Date 2025/8/6 22:37
 * @Version 1.0.0
 */
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/homework", "root", "")
                .globalConfig(builder -> builder
                        .author("Rhh")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/module/src/main/java")
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir()
                )
                .packageConfig(builder -> builder
                        .parent("com.itrhh.module")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                       //.serviceImpl("service")
                        .xml("mapper")

                        .pathInfo(Collections.singletonMap(OutputFile.xml, "E://javaproject/noodle/module/src/main/resources/mapper"))
                )
                .strategyConfig(builder -> builder
                        .addInclude("category")
                        .entityBuilder().enableLombok()
                        .enableFileOverride()
                        .mapperBuilder()
                        .enableFileOverride()
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .enableFileOverride()
                        .controllerBuilder()
                        .build())

                .templateEngine(new FreemarkerTemplateEngine())
                .templateConfig(builder -> {
                    builder.service("templates/service.java");
                    builder.mapper("templates/mapper.java");
                    builder.xml("templates/mapper.xml");
                   // builder.controller("templates/controller");
                    builder.serviceImpl(null);
                    builder.controller(null);
                })
                .execute();
    }
}