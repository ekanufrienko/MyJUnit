package com.rufus.myjunit.junit;

import javax.lang.model.type.NullType;
import java.lang.reflect.Method;

public class TestThread extends Thread {

    public TestThread(int threadNumber) {
        super("TestThread №" + threadNumber);
        setPriority(1);
    }

    public void run() {
        int numberTask;
        int numberClass;

        while (true) {

            synchronized (Main.stackTasks) {
                if (Main.stackTasks.isEmpty()) {
                    if (Main.parseMethodsFinished) {
                        currentThread().interrupt();
                    }
                    try {
                        sleep(30);
                        continue;
                    } catch (InterruptedException e) {
                        return;
                    }
                } else {
                    numberTask = Main.stackTasks.pollFirst();
                }
            }

            numberClass = Main.testTasks.get(numberTask).getClassNumber();
            Class<?> classItem = Main.testClasses.get(numberClass).getClassItem();
            Object classObject;
            try {
                classObject = classItem.getConstructor().newInstance();
            } catch (Throwable t) {
                System.out.println("There's no constructor in the Class " + classItem.getName());
                continue;
            }

            //Test for Before Method
            try {
                Method beforeMethod = Main.testClasses.get(numberClass).getBeforeMethod();
                if (beforeMethod != null) {
                    beforeMethod.invoke(classObject);
                }
            } catch (Throwable t) {
                System.out.println("Before method didn't come test");
                continue;
            }

            //Test for test method
            String result;
            try {
                Method testMethod = Main.testTasks.get(numberTask).getTestMethod();
                testMethod.invoke(classObject);
                if (Main.testTasks.get(numberTask).getExpectedException() == NullType.class) {
                    result = "Test passed. Correct answer.";
                } else {
                    result = "Test failed. Wrong answer, exception expected.";
                }
            } catch (Throwable t) {
                if (Main.testTasks.get(numberTask).getExpectedException() == t.getCause().getClass()) {
                    result = "Test passed. Correct answer. Catched " + t.getCause().getClass().getName();
                } else {
                    if (AssertNotEqualsError.class == t.getCause().getClass()
                            | AssertNotTrueError.class == t.getCause().getClass()) {
                        result = "Test failed. Wrong answer.";
                    } else {
                        result = "Test failed. Wrong answer. Cathced " + t.getCause().getClass().getName();
                    }
                }
            }
            Main.testTasks.get(numberTask).setTestResult(result);
            System.out.println("Class: " + classItem.getName() + "  Test: "
                    + Main.testTasks.get(numberTask).getTestMethod().getName() + "   " + result);

            //Test for After Method
            try {
                Method afterMethod = Main.testClasses.get(numberClass).getAfterMethod();
                if (afterMethod != null) {
                    afterMethod.invoke(classObject);
                }
            } catch (Throwable t) {
                System.out.println("After method didn't come test");
            }

        }
    }

}
