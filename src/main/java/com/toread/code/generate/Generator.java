package com.toread.code.generate;

import javax.persistence.EntityManager;

/**
 * 生成代码接口
 */
public interface Generator {
    void generate(Class<?> entity);
}
