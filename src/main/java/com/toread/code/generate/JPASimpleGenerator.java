/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-21
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.code.generate;

import com.toread.code.analyze.EntityAnalyze;
import com.toread.code.config.GeneratorControl;
import com.toread.code.config.IOConfig;
import com.toread.code.context.Context;
import com.toread.code.context.GeneratorContext;
import com.toread.code.meta.Entity;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: AbstractGenerator
 * @Description:
 * @author lizhibing
 * @date 2015-05-21 22:12 
 */
public  class JPASimpleGenerator implements JavaCodeGenerator,WebCodeGenerator{
    private static final Logger logger = Logger.getLogger(JPASimpleGenerator.class);

    public static final String ENTITY_MANAGER_INSTANCE = "entity_manager_instance";
    public static final String IO_CONFIG_INSTANCE = "io_config_instance";
    public static final String GC = "gc";

    public static final String ENTITY = "entity";
    public static final Pattern PATH_PATTERN= Pattern.compile("(\\$\\{([^\\}]+)\\})");

    protected Context context;
    public JPASimpleGenerator(){
        context  = new GeneratorContext();
    }
    public Context getContext(){return context;}
    protected Entity getEntityInfo(Class<?> entityClass) {
        EntityAnalyze entityAnalyze =  new EntityAnalyze();
        EntityManager entityManager = (EntityManager)context.getAttribute(ENTITY_MANAGER_INSTANCE);
        Entity entity  = (Entity)context.getAttribute(ENTITY);
        Boolean entityNullAble = entity==null;
        Boolean isTagType = (entity!=null&&entityClass.equals(entity.getType()));
        if(entityNullAble||!isTagType){
            entity = entityAnalyze.analyzeOneEntity(entityClass, entityManager);
        }
        return entity;
    }

    private void jcGenerate(Entity entity){
        IOConfig ioConfig = (IOConfig)context.getAttribute(IO_CONFIG_INSTANCE);
        File javaCodeTR = ioConfig.getJavaTemplateRootPath();
        File javaCodeOR = ioConfig.getJavaOutPutRootPath();
        outPutAllTemplateFile(entity, javaCodeTR, javaCodeOR);
    }

    private void wcGenerate(Entity entity) {
        IOConfig ioConfig = (IOConfig)context.getAttribute(IO_CONFIG_INSTANCE);
        File javaCodeTR = ioConfig.getWebTemplateRootPath();
        File javaCodeOR = ioConfig.getWebOutPutRootPath();
        outPutAllTemplateFile(entity, javaCodeTR, javaCodeOR);
    }

    protected Configuration initConfig(File templateRoot) throws IOException {
        Configuration cf = new Configuration();
        cf.setDirectoryForTemplateLoading(templateRoot);
        return cf;
    }

    private void outPutAllTemplateFile(Entity entity, File codeTR, File codeOR) {
        Configuration cfg;
        try {
            cfg = initConfig(codeTR);
            List<String> templates = getTemplates(codeTR);
            for (String template : templates) {
                Map<String,Object> dataMap = createDataModel((Entity)BeanUtils.cloneBean(entity));
                Template tp = cfg.getTemplate(template);
                modificationGc(dataMap,tp);
                template = reallyFilePath(template,dataMap);
                File file = new File(codeOR,template);
                gtOneTemplate(dataMap,tp,file);
            }
        } catch (IOException e) {
            throw  new  RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw  new  RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw  new  RuntimeException(e);
        } catch (InstantiationException e) {
            throw  new  RuntimeException(e);
        } catch (TemplateException e) {
            throw  new  RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw  new  RuntimeException(e);
        }
    }


    private Map<String,Object> createDataModel(Entity entity) {
        Map<String,Object> dataModel = new HashMap<String, Object>();
        dataModel.put(ENTITY,entity);
        dataModel.put(GC,new GeneratorControl());
        return dataModel;
    }

    protected List<String> getTemplates(File template){
        List<File> allFile = new ArrayList<File>();
        List<String> templateName = new ArrayList<String>();
        allFile = getTemplateFiles(template, allFile);
        for (File file : allFile) {
            templateName.add(file.getAbsolutePath().replace(template.getAbsolutePath(),""));
        }
        return templateName;
    }

    protected String reallyFilePath(String filePath,Map<String,Object> dataModel){
        Matcher matcher = PATH_PATTERN.matcher(filePath);
        String temp = new String(filePath);
        while(matcher.find()){
            String replace = matcher.group(1);
            String key = matcher.group(2);
            String value = null;
            try {
                value = (String)PropertyUtils.getProperty(dataModel, key);
            } catch (IllegalAccessException e) {
                throw  new  RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw  new  RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw  new  RuntimeException(e);
            }
            temp = filePath.replace(replace, value);
        }
        return temp;
    }

    private List<File> getTemplateFiles(File father,List<File> allFile){
        if(father.isDirectory()){
            File[] files = father.listFiles();
            if(files!=null&&files.length>0){
                for (File file : files) {
                    getTemplateFiles(file, allFile);
                }
            }
        }else{
            allFile.add(father);
        }
        return allFile;
    }

    protected void modificationGc(Map<String,Object> dataModel,Template template)
            throws IOException, TemplateException {
        template.createProcessingEnvironment(dataModel,new StringWriter()).process();
    }

    protected void gtOneTemplate(Map<String,Object> dataModel,Template template,File outPut)
            throws IOException, TemplateException {
        GeneratorControl gc = (GeneratorControl)dataModel.get(GC);
        //一些参数的逻辑处理
        if(gc.isIgnoreOutput()){return;}
        if(StringUtils.hasText(gc.getOutputFile())){
            outPut = new File(gc.getOutputFile());
        }
        if(outPut.exists()){
            if(gc.isOverride()){
                template.process(dataModel,new FileWriter(outPut));
            }
        }else {
            //创建路径
            if(!outPut.exists()){outPut.getParentFile().mkdirs();outPut.createNewFile();}
            template.process(dataModel,new FileWriter(outPut));
        }
    }

    @Override
    public void javaCodeGenerate(Class<?> entity) {
        Entity entityInfo = getEntityInfo(entity);
        jcGenerate(entityInfo);
    }

    @Override
    public void webCodeGenerate(Class<?> entity) {
        Entity entityInfo = getEntityInfo(entity);
        wcGenerate(entityInfo);
    }
}
