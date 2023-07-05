package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

/**
 * @author Wes Lin
 */
public class IsNull extends AbstractNoValueCondition {

    public IsNull(Column column) {
        super(column);
    }

    @Override
    public String getCondition() {
        return "is null";
    }

}
