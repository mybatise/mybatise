package io.github.mybatise.processor;

import io.github.mybatise.processor.generators.TemplateFilesCreator;
import io.github.mybatise.processor.model.MappingConfig;

/**
 * @author Wes Lin
 */
public class JavaCodegen extends MybatisCodegen {


    public JavaCodegen(MappingConfig mappingConfig, TemplateFilesCreator templateFilesCreator) {
        super(mappingConfig, templateFilesCreator);
    }
}
