package com.rufus.myjunit.test;

import com.rufus.myjunit.junit.*;

public class TestClass {

    @Before
    public void BeforeVoid() {
        System.out.println("\"" + Thread.currentThread().getName() + "\" begin testing");
    }

    @Test(expectedException = ArithmeticException.class)
    public void SimpleTest_0() {
        int error = 1/0;
        Assert.assertTrue(1 == 2);
    }

    @Test
    public void SimpleTest_1() {
        Assert.assertTrue(1 == 2);
    }

    @Test
    public void SimpleTest_2() {
        Assert.assertEquals(1, 2);
    }

    @Test
    public void SimpleTest_3() {
        int number = 1;
        Assert.assertEquals(number, number);
    }

    @Ignore
    @Test
    public void SimpleTest_4() {
        int number = 2;
        Assert.assertEquals(number, number);
    }

    @Test(expectedException = IndexOutOfBoundsException.class)
    public void wrongExceptionTest() {
        int error = 1/0;
        Assert.assertTrue(1 == 1);
    }

    @After
    public void AfterVoid() {
        System.out.println("\"" + Thread.currentThread().getName() + "\" finished testing");
    }

}
