package com.toread.code.meta;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class FieldTest {

    protected Field field;

    @Before
    public void init(){
        field = new Field();
        field.setCode("code");
        field.setComment("传奇世界");
        field.setPk(false);
    }

    @Test
    public void testIsStringType() throws Exception {
        field.setType(String.class);
        Assert.assertTrue(field.isStringType());
        Assert.assertTrue(!field.isDateType());
    }

    @Test
    public void testIsDateType() throws Exception {
        field.setType(Date.class);
        Assert.assertTrue(field.isDateType());
        field.setType(String.class);
        Assert.assertTrue(!field.isDateType());
    }

    @Test
    public void testIsNumberType() throws Exception {
        field.setType(Long.class);
        Assert.assertTrue(field.isNumberType());
    }
}