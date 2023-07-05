package io.github.mybatise.core;

import io.github.mybatise.core.condition.Condition;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Wes Lin
 */
public abstract class Support<T> {

    public abstract String getTable();

    protected Set<Column> fields = new LinkedHashSet<>();
    protected List<Condition> conditions = new ArrayList<>();
    protected T value;

    public Set<Column> getFields() {
        return fields;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public T getValue() {
        return value;
    }

}
