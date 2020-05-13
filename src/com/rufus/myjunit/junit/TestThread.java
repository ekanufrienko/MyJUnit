package com.rufus.myjunit.junit;

import java.lang.reflect.Method;

public class TestThread extends Thread {

    public TestThread(int threadNumber) {
        super("TestThread №" + threadNumber);
        setPriority(1);
    }

    public void run() {
        TestTask testTask;

        while (true) {

            synchronized (Main.testTasks) {
                if (Main.testTasks.isEmpty()) {
                    if (Main.parseMethodsFinished) {
                        return;
                    }
                    try {
                        sleep(30);
                        continue;
                    } catch (InterruptedException e) {
                        return;
                    }
                } else {
                    testTask = Main.testTasks.pollFirst();
                }
            }

            Class<?> classItem = testTask.getTestClass().getClassItem();
            Object classObject;
            try {
                classObject = classItem.getConstructor().newInstance();
            } catch (Throwable t) {
                System.out.println("There's no constructor in the Class " + classItem.getName());
                continue;
            }

            //Test for Before Method
            try {
                Method beforeMethod = testTask.getTestClass().getBeforeMethod();
                if (beforeMethod != null) {
                    beforeMethod.invoke(classObject);
                }
            } catch (Throwable t) {
                System.out.println("Class: " + classItem.getName() + "  Before Method     Error: After method throws " + t);
                continue;
            }

            //Test for test method
            String result;
            try {
                Method testMethod = testTask.getTestMethod();
                testMethod.invoke(classObject);
                if (testTask.getExpectedException() == DefaultException.class) {
                    result = "Test passed. Correct answer.";
                } else {
                    result = "Test failed. Wrong answer, exception expected.";
                }
            } catch (Throwable t) {
                if (testTask.getExpectedException().isAssignableFrom(t.getCause().getClass())) {
                    result = "Test passed. Correct answer. Catched " + t.getCause().getClass().getName();
                } else {
                    if (AssertException.class.isAssignableFrom(t.getCause().getClass())) {
                        result = "Test failed. Wrong answer.";
                    } else {
                        result = "Test failed. Wrong Exception. Cathced " + t.getCause().getClass().getName()
                                + ", but expected " + testTask.getExpectedException().getName();
                    }
                }
            }
            testTask.setTestResult(result);
            System.out.println("Class: " + classItem.getName() + "  Test: "
                    + testTask.getTestMethod().getName() + "   " + result);

            //Test for After Method
            try {
                Method afterMethod = testTask.getTestClass().getAfterMethod();
                if (afterMethod != null) {
                    afterMethod.invoke(classObject);
                }
            } catch (Throwable t) {
                System.out.println("Class: " + classItem.getName() + "  After Method     Error: After method throws " + t);
            }
        }
    }

}
