package com.toread.code.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-13
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
@Documented
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RUNTIME)
public @interface Comment{
    String value() default "";
}
