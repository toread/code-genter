package com.toread.code.generate;

import com.toread.code.config.IOConfig;
import com.toread.code.spring.SpringConfig;
import com.toread.test.Account;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Log4jConfigurer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class JPASimpleGeneratorTest {
    protected JPASimpleGenerator jG;
    //初始化数日志
    static {
        try {
            Log4jConfigurer.initLogging("classpath:log4j.properties");


        } catch (FileNotFoundException ex) {
            System.err.println("未能在类根路径加载log4j.xml,请构建该文件");
        }
    }
    @Before
    public void init(){
        jG = new JPASimpleGenerator();
        IOConfig ioConfig = new IOConfig();
        ioConfig.setJavaOutPutRootPath(
                new File("D:\\个人项目\\codeGen\\src\\main\\java\\com\\toread\\code"));
        ioConfig.setJavaTemplateRootPath(
                new File("D:\\个人项目\\codeGen\\src\\main\\resources\\tempalte"));
        ioConfig.setWebOutPutRootPath(new File("D:\\个人项目\\codeGen\\src\\main\\web"));
        ioConfig.setWebTemplateRootPath(
                new File("D:\\个人项目\\codeGen\\src\\main\\resources\\tempalte"));

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        EntityManager entityManager = applicationContext.getBean(EntityManagerFactory.class).createEntityManager();
        jG.getContext().setAttribute(JPASimpleGenerator.IO_CONFIG_INSTANCE,ioConfig);
        jG.getContext().setAttribute(JPASimpleGenerator.ENTITY_MANAGER_INSTANCE,entityManager);
    }

    @Test
    public void testJavaCodeGenerate() throws Exception {
        jG.javaCodeGenerate(Account.class);
    }

    @Test public void testWebCodeGenerate() throws Exception {
        jG.webCodeGenerate(Account.class);
    }
}