package com.rufus.myjunit.junit;

public class Assert {
    /**
     * @param condition булево значение, которое нужно проверить.
     * @throws AssertNotTrueError передано ложное значение.
     */
    public static void assertTrue(boolean condition) throws AssertNotTrueError {
        if (!condition) {
            throw new AssertNotTrueError();
        }
    }

    /**
     * @param a первый объект для сравнения.
     * @param b второй объект для сравнения.
     * @throws AssertNotEqualsError выкидывается, когда объекты не равны.
     */
    public static void assertEquals(Object a, Object b) throws AssertNotEqualsError {
        if (!a.equals(b)) {
            throw new AssertNotEqualsError();
        }
    }
}
