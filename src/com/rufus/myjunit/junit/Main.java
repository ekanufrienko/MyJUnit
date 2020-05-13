package com.rufus.myjunit.junit;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Исполняемый файл. Аналог JUnit библиотеки.
 * В тестовом файле не должно быть больше одного метода с аннотацией @Before. Аналогично с @After.
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
        boolean beforeMethodFound = false;
        boolean afterMethodFound = false;

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
                if (beforeMethodFound){
                    System.out.println("Class: " + testClass + "   Error: Expected one @Before method");
                }
                testClass.setBeforeMethod(method);
                beforeMethodFound = true;
            } else if (method.getAnnotation(After.class) != null) {
                if (afterMethodFound){
                    System.out.println("Class: " + testClass + "   Error: Expected one @After method");
                }
                testClass.setAfterMethod(method);
                afterMethodFound = true;
            }
        }

        for (Method method : methods) {
            Test annotation = method.getAnnotation(Test.class);
            if (annotation != null && method.getAnnotation(Ignore.class) == null) {
                synchronized (testTasks) {
                    testTasks.add(new TestTask(testClass, method, annotation.expectedException()));
                }
            }
        }
    }

}
