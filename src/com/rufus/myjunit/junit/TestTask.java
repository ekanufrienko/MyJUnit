package com.rufus.myjunit.junit;

import java.lang.reflect.Method;

public class TestTask {
    private int classNumber;
    private Method testMethod;
    private String testResult;
    private Class<?> expectedException;

    public TestTask(int classNumber, Method testMethod, Class<?> expectedException) {
        this.classNumber = classNumber;
        this.testMethod = testMethod;
        this.testResult = null;
        this.expectedException = expectedException;
    }

    public int getClassNumber() {
        return classNumber;
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
