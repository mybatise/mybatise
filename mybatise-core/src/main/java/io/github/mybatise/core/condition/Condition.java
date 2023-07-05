package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

/**
 * @author Wes Lin
 */
public interface Condition {

    enum Type {
        NO_VALUE,
        SINGLE_VALUE,
        BETWEEN_VALUE,
        LIST_VALUE
    }

    Type getType();

    String getCondition();

    Column getColumn();

    String render(String placeholder);

}
