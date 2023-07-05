package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

/**
 * @author Wes Lin
 */
public class IsNotNull extends AbstractNoValueCondition {

    public IsNotNull(Column column) {
        super(column);
    }

    @Override
    public String getCondition() {
        return "is not null";
    }
}
