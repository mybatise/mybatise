package io.github.mybatise.processor.mapper;

import io.github.mybatise.processor.model.definition.EntityDefinition;
import io.github.mybatise.processor.model.MappingContext;
import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.mybatise.processor.model.DataModelFields.CLASS_NAME;
import static io.github.mybatise.processor.model.DataModelFields.ENTITY_TYPE_NAME;
import static io.github.mybatise.processor.model.DataModelFields.FIELDS;
import static io.github.mybatise.processor.model.DataModelFields.IMPORTS;
import static io.github.mybatise.processor.model.DataModelFields.PACKAGE;
import static io.github.mybatise.processor.model.DataModelFields.PK_FIELD;
import static io.github.mybatise.processor.model.DataModelFields.RESULT_MAP_ID_NAME;
import static io.github.mybatise.processor.model.DataModelFields.SUPPORT_TYPE_NAME;
import static io.github.mybatise.processor.model.DataModelFields.TABLE_NAME;


/**
 * @author Wes Lin
 */
public class DefinitionToDataModelMapper {

    private List<String> getImports(EntityDefinition entityDef){
        boolean jdbcImport = entityDef.getFieldDefinitions()
                .stream().anyMatch(definition -> definition.getJdbcType() != JdbcType.UNDEFINED);
        List<String> imports = new ArrayList<>();
        if (jdbcImport) {
            imports.add("org.apache.ibatis.type.JdbcType");
        }
        return imports;
    }

    public Map<String, Object> mapSupport(MappingContext context,
                                          EntityDefinition entityDef) {
        Map<String, Object> dataModel = new HashMap<>();
        String className = entityDef.getTypeSimpleName() + context.getSupportSuffix();
        dataModel.put(PACKAGE, context.getPackageName());
        dataModel.put(CLASS_NAME, className);
        dataModel.put(FIELDS, entityDef.getFieldDefinitions());
        dataModel.put(ENTITY_TYPE_NAME, entityDef.getTypeName());
        dataModel.put(TABLE_NAME, entityDef.getTableName());
        return dataModel;
    }

    public Map<String, Object> mapMapper(MappingContext context,
                                         EntityDefinition entityDef) {
        Map<String, Object> dataModel = new HashMap<>();
        String className = entityDef.getTypeSimpleName() + context.getMapperSuffix();
        String supportTypeName = entityDef.getTypeSimpleName() + context.getSupportSuffix();
        String resultMapId = entityDef.getTypeSimpleName() + "ResultMap";
        dataModel.put(PACKAGE, context.getPackageName());
        dataModel.put(CLASS_NAME, className);
        dataModel.put(FIELDS, entityDef.getFieldDefinitions());
        dataModel.put(PK_FIELD, entityDef.getPKField());
        dataModel.put(ENTITY_TYPE_NAME, entityDef.getTypeName());
        dataModel.put(SUPPORT_TYPE_NAME, supportTypeName);
        dataModel.put(RESULT_MAP_ID_NAME, resultMapId);
        dataModel.put(IMPORTS, getImports(entityDef));
        return dataModel;
    }

}
