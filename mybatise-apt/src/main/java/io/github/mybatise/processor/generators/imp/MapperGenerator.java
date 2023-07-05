package io.github.mybatise.processor.generators.imp;

import io.github.mybatise.processor.generators.FilesGenerator;
import io.github.mybatise.processor.generators.TemplateFilesCreator;
import io.github.mybatise.processor.generators.TemplateType;
import io.github.mybatise.processor.mapper.DefinitionToDataModelMapper;
import io.github.mybatise.processor.model.definition.EntityDefinition;
import io.github.mybatise.processor.model.MappingContext;

import java.util.Map;

/**
 * @author Wes Lin
 */
public class MapperGenerator implements FilesGenerator {
    @Override
    public void generate(TemplateFilesCreator filesCreator, MappingContext context, EntityDefinition definition) {
        DefinitionToDataModelMapper dataModelMapper = new DefinitionToDataModelMapper();
        Map<String, Object> dataModel = dataModelMapper.mapMapper(context, definition);
        filesCreator.creat(context, TemplateType.MAPPER, dataModel);
    }
}
