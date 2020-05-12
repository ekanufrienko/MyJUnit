package com.rufus.myjunit.junit;

public class Assert {
    /**
     * @param condition булево значение, которое нужно проверить.
     * @throws AssertNotTrueError передано ложное значение.
     */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertNotTrueError();
        }
    }

    /**
     * @param a первый объект для сравнения.
     * @param b второй объект для сравнения.
     * @throws AssertNotEqualsError выкидывается, когда объекты не равны.
     */
    public static void assertEquals(Object a, Object b) {
        if (!a.equals(b)) {
            throw new AssertNotEqualsError();
        }
    }
}
