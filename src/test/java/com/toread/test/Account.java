/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-04
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.test;


import com.toread.code.annotation.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author lizhibing
 * @ClassName: Account
 * @Description:
 * @date 2015-05-04 15:45
 */
@Entity
@Comment("账户管理")
public class Account{
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Comment("主键")
    private String uuid;

    @Comment("代码")
    private String code;

    @Comment("密码")
    private String pwd;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
