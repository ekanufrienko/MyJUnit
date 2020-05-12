package com.rufus.myjunit.junit;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Исполняемый файл. Аналог JUnit библиотеки.
 * Пример ввода команды запуска из командной строки: java -cp "MyJUnit.jar;"
 * myjunit.junit 3 com.rufus.myjunit.test.TestClass
 * где 3 - количество тредов, на которых запускается процесс.
 */
public class Main {

    /**
     * Список всех тестируемых методов.
     */
    static final Deque<TestTask> testTasks = new ArrayDeque<>();
    volatile static boolean parseMethodsFinished = false;


    public static void main(String[] args) {

        int length = args.length;
        if (args.length == 0) {
            return;
        }

        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            new TestThread(i + 1).start();
        }

        for (int i = 1; i < args.length; i++) {
            parseMethods(args[i]);
            parseMethodsFinished = true;
        }

    }

    /**
     * По имени класса находит все тестируемые методы класса, методы, выполняемые до и после тестирования.
     * Добавляет отдельные методы в очередь задач.
     *
     * @param className переданное имя класса.
     */
    public static void parseMethods(String className) {

        TestClass testClass;
        try {
            testClass = new TestClass(Class.forName(className));
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound");
            return;
        }

        Method[] methods = testClass.getClassItem().getMethods();

        for (Method method : methods) {
            if (method.getAnnotation(Before.class) != null) {
                testClass.setBeforeMethod(method);
                continue;
            }
            if (method.getAnnotation(After.class) != null) {
                testClass.setAfterMethod(method);
            }
        }

        for (Method method : methods) {
            synchronized (testTasks) {
                Test annotation = method.getAnnotation(Test.class);
                if (annotation != null && method.getAnnotation(Ignore.class) == null) {
                    testTasks.add(new TestTask(testClass, method, annotation.expectedException()));
                }
            }
        }
    }

}
