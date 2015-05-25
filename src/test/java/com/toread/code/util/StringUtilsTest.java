package com.toread.code.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test public void testFistUpperCase() throws Exception {
        String  test = "xbBB";
        Assert.assertEquals(StringUtils.fistUpperCase(test),"XbBB");
    }

    @Test public void testFistLowerCase() throws Exception {
        String  test = "传奇世界";
        Assert.assertEquals(StringUtils.fistLowerCase(test),"xbBB");
    }
}