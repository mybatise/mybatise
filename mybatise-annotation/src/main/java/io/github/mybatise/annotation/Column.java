package io.github.mybatise.annotation;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.UnknownTypeHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Wes Lin
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface Column {

    /**
     *
     * @return name of column
     */
    String name() default "";

    /**
     *
     * @return the column is the primary key
     */
    boolean id() default false;

    JdbcType jdbcType() default JdbcType.UNDEFINED;

    Class<? extends TypeHandler> typeHandler() default UnknownTypeHandler.class;

}