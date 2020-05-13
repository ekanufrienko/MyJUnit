package com.rufus.myjunit.test;

import com.rufus.myjunit.junit.*;

public class TestClass {

    @Before
    public void beforeTestFunction() {
        System.out.println("\"" + Thread.currentThread().getName() + "\" begin testing");
    }

    @Test(expectedException = ArithmeticException.class)
    public void expectedExceptionTest() {
        int error = 1/0;
        Assert.assertTrue(1 == 2);
    }

    @Test
    public void wrongAnswerCompareTest() {
        Assert.assertTrue(1 == 2);
    }

    @Test
    public void wrongAnswerEqualsTest() {
        Assert.assertEquals(1, 2);
    }

    @Test
    public void correctAnswerEqualsTest() {
        int number = 1;
        Assert.assertEquals(number, number);
    }

    @Ignore
    @Test
    public void ignoreAnnotationTest() {
        int number = 2;
        Assert.assertEquals(number, number);
    }

    @Test(expectedException = IndexOutOfBoundsException.class)
    public void wrongExceptionTest() {
        int error = 1/0;
        Assert.assertTrue(1 == 1);
    }

    @After
    public void afterTestFunction() {
        System.out.println("\"" + Thread.currentThread().getName() + "\" finished testing");
    }

}
