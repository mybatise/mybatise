package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

import java.util.List;

/**
 * @author Wes Lin
 */
public class IsNotIn<T> extends AbstractListValueCondition<T> {

    public IsNotIn(Column column, List<T> value) {
        super(column, value);
    }

    @Override
    public String getCondition() {
        return "not in";
    }
}
