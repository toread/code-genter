package com.toread.code.meta;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 实体类的计算结果信息
 */
public class Entity{
    private String comment;
    private String code;
    private List<Field> fields;
    private Class<?>  type;

    public Integer excludeIdFiledTotal(){
        Integer total = 0;
        if(!CollectionUtils.isEmpty(fields)){
            for (Field field : fields) {
                if (!field.getPk()){total++;}
            }
        }
        return total;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
