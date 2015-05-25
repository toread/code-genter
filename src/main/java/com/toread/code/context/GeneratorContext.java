/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-21
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.code.context;

import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AbstractContext
 * @Description:
 * @author lizhibing
 * @date 2015-05-21 22:16 
 */
public  class GeneratorContext implements Context{
    public static final String IO_CONFIG="io_config";

    protected Map map;
    public GeneratorContext() {
        map = new HashMap();
    }

    @Override
    public Object getAttribute(String var1) {
        Object object= map.get(var1);
        Assert.notNull("不存在:"+var1+"的值");
        return object;
    }

    @Override
    public void setAttribute(String var1, Object var2) {
        map.put(var1,var2);
    }

    @Override
    public Object removeAttribute(String var1) {
        return map.remove(var1);
    }
}
