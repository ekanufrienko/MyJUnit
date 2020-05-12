package com.rufus.myjunit.junit;

import java.lang.reflect.Method;

public class TestTask {
    private TestClass testClass;
    private Method testMethod;
    private String testResult;
    private Class<?> expectedException;

    public TestTask(TestClass testClass, Method testMethod, Class<?> expectedException) {
        this.testClass = testClass;
        this.testMethod = testMethod;
        this.testResult = null;
        this.expectedException = expectedException;
    }

    public TestClass getTestClass() {
        return testClass;
    }

    public Method getTestMethod() {
        return testMethod;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public Class<?> getExpectedException() {
        return expectedException;
    }

    public void setExpectedException(Class<?> expectedException) {
        this.expectedException = expectedException;
    }
}
