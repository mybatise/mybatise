package io.github.mybatise.annotation;

import io.github.mybatise.annotation.generators.IdentityGenerator;
import io.github.mybatise.annotation.generators.UUIDHexGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Wes Lin
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface GeneratedValue {

    GenerationType strategy() default GenerationType.IDENTITY;

    String keyStatement() default "";

    boolean before() default false;

    Class<? extends IdentityGenerator> generator() default UUIDHexGenerator.class;

}