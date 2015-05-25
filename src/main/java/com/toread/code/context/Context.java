/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-21
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.code.context;

import java.util.Map;

/**
 * 生成代码的上下文
 */
public interface Context {
    Object getAttribute(String var1);
    void setAttribute(String var1, Object var2);
    Object removeAttribute(String var1);
}
