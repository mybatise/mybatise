package io.github.mybatise.processor.generators;

import io.github.mybatise.processor.model.definition.EntityDefinition;
import io.github.mybatise.processor.model.MappingContext;

/**
 * @author Wes Lin
 */
@FunctionalInterface
public interface FilesGenerator {

    void generate(TemplateFilesCreator templateFilesCreator, MappingContext context, EntityDefinition definition);
}
