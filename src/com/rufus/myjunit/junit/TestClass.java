package com.rufus.myjunit.junit;

import java.lang.reflect.Method;

public class TestClass {
    private final Class<?> classItem;
    private Method afterMethod;
    private Method beforeMethod;

    public TestClass(Class<?> classItem) {
        this.classItem = classItem;
        this.afterMethod = null;
        this.beforeMethod = null;
    }

    public Class<?> getClassItem() {
        return classItem;
    }

    public Method getAfterMethod() {
        return afterMethod;
    }

    public void setAfterMethod(Method afterMethod) {
        this.afterMethod = afterMethod;
    }

    public Method getBeforeMethod() {
        return beforeMethod;
    }

    public void setBeforeMethod(Method beforeMethod) {
        this.beforeMethod = beforeMethod;
    }
}
