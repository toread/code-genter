/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-14
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.code.util;

/**
 * @ClassName: StringUtils
 * @Description:
 * @author lizhibing
 * @date 2015-05-14 14:33 
 */
public abstract class StringUtils {
    public  final  static String fistUpperCase(String string){
        char[] cs=string.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

    public final  static String fistLowerCase(String string){
        char[] cs=string.toCharArray();
        cs[0]+=32;
        return String.valueOf(cs);
    }
}
