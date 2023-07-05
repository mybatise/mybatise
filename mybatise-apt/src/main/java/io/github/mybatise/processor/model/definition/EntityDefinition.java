package io.github.mybatise.processor.model.definition;

import java.util.List;

/**
 * @author Wes Lin
 */
public class EntityDefinition {

    private List<FieldDefinition> fieldDefinitions;

    private String tableName;
    private String typeName;

    public EntityDefinition(String tableName,
                            String typeName,
                            List<FieldDefinition> fieldDefinitions) {
        this.tableName = tableName;
        this.typeName = typeName;
        this.fieldDefinitions = fieldDefinitions;
    }

    public List<FieldDefinition> getFieldDefinitions() {
        return fieldDefinitions;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypeSimpleName() {
        return typeName.substring(typeName.lastIndexOf(".") + 1);
    }

    public FieldDefinition getPKField() {
        return fieldDefinitions.stream()
                .filter(FieldDefinition::getId)
                .findFirst()
                .orElse(null);
    }

}
