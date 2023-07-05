package io.github.mybatise.core.condition;

import io.github.mybatise.core.Column;

import java.util.List;

/**
 * @author Wes Lin
 */
public abstract class AbstractListValueCondition<T> extends AbstractSingleValueCondition<List<T>> {

    protected AbstractListValueCondition(Column column, List<T> value) {
        super(column, value);
    }

    @Override
    public Type getType() {
        return Type.LIST_VALUE;
    }

    @Override
    public String render(String placeholder) {
        StringBuilder sb = new StringBuilder(String.format("%s %s", column.getName(), getCondition()));
        sb.append(String.format("\n<foreach collection=\"%s.value\" item=\"listItem\" "
                + "open=\"(\" close=\")\" separator=\",\" >\n"
                + "#{listItem%s}\n"
                + "</foreach>\n", placeholder, column.getProps()));
        return sb.toString();
    }
}
