package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

/**
 * @author Wes Lin
 */
public class IsNotLike<T> extends AbstractSingleValueCondition<T> {

    public IsNotLike(Column column, T value) {
        super(column, value);
    }

    @Override
    public String getCondition() {
        return "not like";
    }
}
