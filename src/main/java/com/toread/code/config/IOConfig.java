/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-21
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.code.config;

import java.io.File;

/**
 * 输入输出控制
 */
public class IOConfig {
    private File javaTemplateRootPath;
    private File javaOutPutRootPath;
    private File webTemplateRootPath;
    private File webOutPutRootPath;

    public File getJavaTemplateRootPath() {
        return javaTemplateRootPath;
    }

    public void setJavaTemplateRootPath(File javaTemplateRootPath) {
        this.javaTemplateRootPath = javaTemplateRootPath;
    }

    public File getJavaOutPutRootPath() {
        return javaOutPutRootPath;
    }

    public void setJavaOutPutRootPath(File javaOutPutRootPath) {
        this.javaOutPutRootPath = javaOutPutRootPath;
    }

    public File getWebTemplateRootPath() {
        return webTemplateRootPath;
    }

    public void setWebTemplateRootPath(File webTemplateRootPath) {
        this.webTemplateRootPath = webTemplateRootPath;
    }

    public File getWebOutPutRootPath() {
        return webOutPutRootPath;
    }

    public void setWebOutPutRootPath(File webOutPutRootPath) {
        this.webOutPutRootPath = webOutPutRootPath;
    }
}
