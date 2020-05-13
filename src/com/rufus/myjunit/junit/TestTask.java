package com.rufus.myjunit.junit;

import java.lang.reflect.Method;

public class TestTask {
    private final TestClass testClass;
    private final Method testMethod;
    private final Class<?> expectedException;
    private String testResult;

    public TestTask(TestClass testClass, Method testMethod, Class<?> expectedException) {
        this.testClass = testClass;
        this.testMethod = testMethod;
        this.expectedException = expectedException;
        this.testResult = null;
    }

    public TestClass getTestClass() {
        return testClass;
    }

    public Method getTestMethod() {
        return testMethod;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public Class<?> getExpectedException() {
        return expectedException;
    }
}
