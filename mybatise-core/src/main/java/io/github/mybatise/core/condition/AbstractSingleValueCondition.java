package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

/**
 * @author Wes Lin
 */
public abstract class AbstractSingleValueCondition<T> implements Condition {

    protected final T value;
    protected final Column column;

    public AbstractSingleValueCondition(Column column, T value) {
        this.column = column;
        this.value = value;
    }


    public T value() {
        return value;
    }

    @Override
    public Type getType() {
        return Type.SINGLE_VALUE;
    }

    @Override
    public Column getColumn() {
        return column;
    }

    @Override
    public String render(String placeholder) {
        return String.format("%s %s #{%s.value%s}", column.getName(), getCondition(), placeholder, column.getProps());
    }
}
