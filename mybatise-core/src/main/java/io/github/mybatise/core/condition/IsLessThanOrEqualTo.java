package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

/**
 * @author Wes Lin
 */
public class IsLessThanOrEqualTo<T> extends AbstractSingleValueCondition<T> {

    public IsLessThanOrEqualTo(Column column, T value) {
        super(column, value);
    }

    @Override
    public String getCondition() {
        return "<=";
    }
}
