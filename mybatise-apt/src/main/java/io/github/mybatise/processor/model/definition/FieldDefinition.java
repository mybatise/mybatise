package io.github.mybatise.processor.model.definition;

import org.apache.ibatis.type.JdbcType;

/**
 * @author Wes Lin
 */
public class FieldDefinition {

    private String name;
    private String type;
    private String column;
    private Boolean id;

    private KeyGeneratorDefinition keyGenerator;

    private String typeHandler;
    private JdbcType jdbcType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Boolean getId() {
        return id;
    }

    public void setId(Boolean id) {
        this.id = id;
    }

    public KeyGeneratorDefinition getKeyGenerator() {
        return keyGenerator;
    }

    public void setKeyGenerator(KeyGeneratorDefinition keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public String getTypeHandler() {
        return typeHandler;
    }

    public void setTypeHandler(String typeHandler) {
        this.typeHandler = typeHandler;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(JdbcType jdbcType) {
        this.jdbcType = jdbcType;
    }
}
