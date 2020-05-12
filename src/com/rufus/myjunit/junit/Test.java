package com.rufus.myjunit.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * В качестве параметра к @Test можно написать класс исключения, если ожидается он или один из его наследников.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    Class<? extends Throwable> expectedException() default DefaultException.class;
}

