package com.toread.code.meta; /**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-13
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */

import java.util.Date;

/**
 * @ClassName: com.toread.code.meta.FiledInfo
 * @Description:
 * @author lizhibing
 * @date 2015-05-13 17:06 
 */
public class Field {
    private String comment;
    private String code;
    private Class  type;
    private Boolean pk;

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

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Boolean getPk() {
        return pk;
    }

    public void setPk(Boolean pk) {
        this.pk = pk;
    }

    public boolean isStringType(){
        return type.equals(String.class);
    }
    public boolean isDateType(){
        return type.equals(Date.class);
    }

    public boolean isNumberType(){
        return hasSuperClass(type,Number.class);
    }

    public boolean hasSuperClass(Class<?> tClass,Class<?> superClass){
        Class<?> supperTClass = tClass.getSuperclass();
        if(superClass!=null){
            Boolean tempHas =  supperTClass.equals(superClass);
            if(tempHas==true){
                return true;
            }else {
                hasSuperClass(supperTClass,superClass);
            }
        }
        return false;
    }
}
