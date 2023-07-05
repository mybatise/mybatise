package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

/**
 * @author Wes Lin
 */
public abstract class AbstractBetweenValueCondition<T> implements Condition {

    protected final Column column;
    protected final T value;
    protected final T secondValue;

    protected AbstractBetweenValueCondition(Column column, T value, T secondValue) {
        this.column = column;
        this.value = value;
        this.secondValue = secondValue;
    }

    public T getValue() {
        return value;
    }

    public T getSecondValue() {
        return secondValue;
    }

    @Override
    public Type getType() {
        return Type.BETWEEN_VALUE;
    }

    @Override
    public Column getColumn() {
        return column;
    }

    @Override
    public String render(String placeholder) {
        return String.format("%s %s #{%s.value%s} and #{%s.secondValue%s}"
                , column.getName(), getCondition()
                , placeholder, column.getProps()
                , placeholder, column.getProps());
    }
}
