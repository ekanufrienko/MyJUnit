package com.rufus.myjunit.test;

import com.rufus.myjunit.junit.*;

public class TestClass {

    @Before
    public void BeforeVoid() {
        System.out.println("\"" + Thread.currentThread().getName() + "\" begin testing");
    }

    @Test(exp = ArithmeticException.class)
    public void SimpleTest_0() throws AssertNotTrueError {
        int error = 1/0;
        Assert.assertTrue(1 == 2);
    }

    @Test
    public void SimpleTest_1() throws AssertNotTrueError {
        Assert.assertTrue(1 == 2);
    }

    @Test
    public void SimpleTest_2() throws AssertNotEqualsError {
        Assert.assertEquals(1, 2);
    }

    @Test
    public void SimpleTest_3() throws AssertNotEqualsError {
        int number = 1;
        Assert.assertEquals(number, number);
    }

    @Ignore
    @Test
    public void SimpleTest_4() throws AssertNotEqualsError {
        int number = 2;
        Assert.assertEquals(number, number);
    }

    @After
    public void AfterVoid() {
        System.out.println("\"" + Thread.currentThread().getName() + "\" finished testing");
    }

}
