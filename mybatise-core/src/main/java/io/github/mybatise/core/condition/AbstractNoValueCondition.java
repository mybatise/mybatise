package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

/**
 * @author Wes Lin
 */
public abstract class AbstractNoValueCondition implements Condition {

    protected final Column column;

    public AbstractNoValueCondition(Column column) {
        this.column = column;
    }

    @Override
    public Type getType() {
        return Type.NO_VALUE;
    }

    @Override
    public Column getColumn() {
        return column;
    }

    @Override
    public String render(String placeholder) {
        return String.format("%s %s", column.getName(), getCondition());
    }
}
