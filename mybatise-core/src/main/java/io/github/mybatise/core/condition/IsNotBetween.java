package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

/**
 * @author Wes Lin
 */
public class IsNotBetween<T> extends AbstractBetweenValueCondition<T> {

    public IsNotBetween(Column column, T value, T secondValue) {
        super(column, value, secondValue);
    }

    @Override
    public String getCondition() {
        return "not between";
    }
}
