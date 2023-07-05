package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

import java.util.List;

/**
 *
 */
public class IsIn<T> extends AbstractListValueCondition<T> {

    public IsIn(Column column, List<T> value) {
        super(column, value);
    }

    @Override
    public String getCondition() {
        return "in";
    }
}
