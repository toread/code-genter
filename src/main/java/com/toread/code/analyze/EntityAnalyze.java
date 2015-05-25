package com.toread.code.analyze; /**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-13
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */

import com.toread.code.annotation.Comment;
import com.toread.code.meta.Entity;
import com.toread.code.meta.Field;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: com.toread.code.analyze.EntityAnalyze
 * @Description:
 * @author lizhibing
 * @date 2015-05-13 17:05 
 */
public class EntityAnalyze {

    /**
     * 通过分析实体的元数据模型创建模型对象
     * @return 模型对象列表
     * @throws NoSuchFieldException
     */
    public List<Entity> analyzeAllEntity(EntityManager entityManager) throws NoSuchFieldException {
        List<Entity> entities = new ArrayList<Entity>();
        Set<EntityType<?>> entityTypes  = entityManager.getMetamodel().getEntities();

        for (EntityType<?> entityType : entityTypes) {
            entities.add(createEntity(entityType));
        }
        return entities;
    };

    public Entity analyzeOneEntity(Class<?> type,EntityManager entityManager){
        EntityType entityType  = entityManager.getMetamodel().entity(type);
        return createEntity(entityType);
    };

    private Entity createEntity(EntityType type){
        Entity entity = new Entity();
        Class<?> entityClass = type.getJavaType();
        String comment = getClassComment(entityClass);
        String code = com.toread.code.util.StringUtils.fistLowerCase(type.getName());
        List<Field> fields = createFields(type);

        entity.setCode(code);
        entity.setComment(comment);
        entity.setType(entityClass);
        entity.setFields(fields);
        return  entity;
    }

    protected String getClassComment(Class<?> type){
        Annotation annotation = type.getAnnotation(Comment.class);
        Assert.notNull(annotation,type.getName()+":需要"+Comment.class+"注解");
        return (String)AnnotationUtils.getValue(annotation,"value");
    }
    protected String getFiledComment(java.lang.reflect.Field field){
        Annotation annotation = field.getAnnotation(Comment.class);
        Assert.notNull(annotation,field.getName()+":需要"+Comment.class+"注解");
        return (String)AnnotationUtils.getValue(annotation,"value");
    }

    protected  List<Field> createFields(EntityType type){
        List<Field> fields = new ArrayList<Field>();
        Set<Attribute<?, ?>> types = type.getAttributes();
        for (Attribute<?, ?> attribute : types) {
            fields.add(createField(type,attribute));
        }
        return fields;
    }

    protected Field createField(EntityType entityType,Attribute attribute){
        Class<?> entityClass = entityType.getJavaType();
        Field field = new Field();
        try {
            java.lang.reflect.Field attributeField =  entityClass.getDeclaredField(attribute.getName());
            SingularAttribute singularAttributes = entityType.getId(entityType.getIdType().getJavaType());
            String comment = getFiledComment(attributeField);
            String code = attributeField.getName();
            Class<?> type = attributeField.getType();
            Boolean isPk = singularAttributes.equals(attribute);
            field.setPk(isPk);
            field.setComment(comment);
            field.setCode(code);
            field.setType(type);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return  field;
    }

}
