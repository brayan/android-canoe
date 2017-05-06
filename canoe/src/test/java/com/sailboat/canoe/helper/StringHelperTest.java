package com.sailboat.canoe.helper;

import org.junit.Test;

import br.com.sailboat.canoe.helper.StringHelper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class StringHelperTest {

    @Test
    public void isNotEmptyReturnsTrue() {
        assertThat(StringHelper.isNotEmpty("test"), is(true));
    }

    @Test
    public void isNotEmptyReturnsFalse() {
        assertThat(StringHelper.isNotEmpty(null), is(false));
        assertThat(StringHelper.isNotEmpty(""), is(false));
        assertThat(StringHelper.isNotEmpty("       "), is(false));
    }

    @Test
    public void isNullOrEmptyReturnsTrue() {
        assertThat(StringHelper.isNullOrEmpty(null), is(true));
        assertThat(StringHelper.isNullOrEmpty(""), is(true));
        assertThat(StringHelper.isNullOrEmpty("      "), is(true));
    }

    @Test
    public void isNullOrEmptyReturnsFalse() {
        assertThat(StringHelper.isNullOrEmpty("test"), is(false));
    }

    @Test
    public void upperCaseFirstLetter() {
        assertThat(StringHelper.upperCaseFirstLetter("test").equals("Test"), is(true));
    }

    @Test
    public void testGetValueOrEmptyString() {
        assertThat(StringHelper.getValueOrEmptyString(null).equals(""), is(true));
        assertThat(StringHelper.getValueOrEmptyString("").equals(""), is(true));
        assertThat(StringHelper.getValueOrEmptyString("test").equals("test"), is(true));
    }

}
