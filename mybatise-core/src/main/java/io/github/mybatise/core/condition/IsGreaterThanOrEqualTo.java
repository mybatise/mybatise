package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

/**
 * @author Wes Lin
 */
public class IsGreaterThanOrEqualTo<T> extends AbstractSingleValueCondition<T> {

    public IsGreaterThanOrEqualTo(Column column, T value) {
        super(column, value);
    }

    @Override
    public String getCondition() {
        return ">=";
    }
}
