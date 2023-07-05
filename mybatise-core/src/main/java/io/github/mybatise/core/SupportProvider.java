package io.github.mybatise.core;

import io.github.mybatise.annotation.generators.IdentityGenerator;
import io.github.mybatise.core.condition.Condition;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.invoker.Invoker;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Wes Lin
 */
@SuppressWarnings("unchecked")
public class SupportProvider {

    private SupportProvider() {

    }

    private static void setId(Object value, Column id) {
        Reflector reflector = new Reflector(value.getClass());
        Invoker idInvoker = reflector.getSetInvoker(id.getProperty());
        Class<? extends IdentityGenerator> identityGeneratorType = id.getIdentityGenerator();
        Reflector identityGenerator = new Reflector(identityGeneratorType);
        try {
            IdentityGenerator instance = (IdentityGenerator) identityGenerator.getDefaultConstructor().newInstance();
            Serializable nexId = instance.generate();
            idInvoker.invoke(value, new Object[]{nexId});
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getValue(Column column) {
        StringBuilder sb = new StringBuilder("#{value.");
        sb.append(column.getProperty());
        sb.append(column.getProps());
        sb.append("}");
        return sb.toString();
    }

    private static String getCondition(List<Condition> conditions) {
        if (conditions == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder("<where>");
        for (int i = 0; i < conditions.size(); i++) {
            Condition condition = conditions.get(i);
            sb.append("and (");
            sb.append(condition.render(String.format("conditions[%s]", i)));
            sb.append(")\n");
        }
        sb.append("</where>");
        return sb.toString();
    }


    public static String select(Support support) {
        Set<Column> fields = support.fields;
        String columns = fields.stream().map(Column::getName).collect(Collectors.joining(","));
        String table = support.getTable();
        String sql = new SQL().SELECT(columns).FROM(table).toString();
        return "<script>" + sql + getCondition(support.conditions) + "</script>";
    }

    public static String insert(Support support) {
        Set<Column> fields = support.fields;
        fields.stream().filter(column -> column.getId() && column.getIdentityGenerator() != null)
                .findFirst().ifPresent(column -> setId(support.value, column));
        String table = support.getTable();
        String columns = fields.stream().map(Column::getName).collect(Collectors.joining(","));
        String values = fields.stream().map(field -> getValue(field)).collect(Collectors.joining(","));
        String sql = new SQL().INTO_COLUMNS(columns).INTO_VALUES(values).INSERT_INTO(table).toString();
        return "<script>" + sql + "</script>";
    }

    public static String update(Support support) {
        List<Condition> conditions = support.conditions;
        if (conditions == null || conditions.size() == 0) {
            throw new IllegalArgumentException("You cannot have no primary key and no conditions");
        }
        Set<Column> fields = support.fields;
        String table = support.getTable();
        String sets = fields.stream().map(field -> field.getName() + " = " + getValue(field))
                .collect(Collectors.joining(","));
        String sql = new SQL().SET(sets).UPDATE(table).toString();
        return "<script>" + sql + getCondition(conditions) + "</script>";
    }

    public static String delete(Support support) {
        List<Condition> conditions = support.conditions;
        if (conditions == null || conditions.size() == 0) {
            throw new IllegalArgumentException("You cannot have no primary key and no conditions");
        }
        String table = support.getTable();
        String sql = new SQL().DELETE_FROM(table).toString();
        return "<script>" + sql + getCondition(conditions) + "</script>";
    }

}
