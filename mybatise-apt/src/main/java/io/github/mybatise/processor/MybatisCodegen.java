package io.github.mybatise.processor;

import io.github.mybatise.processor.generators.FilesGenerator;
import io.github.mybatise.processor.generators.FilesGeneratorFactory;
import io.github.mybatise.processor.generators.TemplateFilesCreator;
import io.github.mybatise.processor.model.definition.EntityDefinition;
import io.github.mybatise.processor.model.MappingConfig;
import io.github.mybatise.processor.model.MappingConfigConstants;
import io.github.mybatise.processor.model.MappingContext;

/**
 * @author Wes Lin
 */
public abstract class MybatisCodegen {

    protected final MappingConfig mappingConfig;
    protected final TemplateFilesCreator templateFilesCreator;

    public MybatisCodegen(MappingConfig mappingConfig, TemplateFilesCreator templateFilesCreator) {
        this.mappingConfig = mappingConfig;
        this.templateFilesCreator = templateFilesCreator;
        initDefaultValues(mappingConfig);
    }

    private void initDefaultValues(MappingConfig mappingConfig) {
        if (mappingConfig.getPackageName() == null) {
            mappingConfig.setPackageName(MappingConfigConstants.DEFAULT_PACK);
        }
        if (mappingConfig.getSupportSuffix() == null) {
            mappingConfig.setSupportSuffix(MappingConfigConstants.DEFAULT_SUPPORT_SUFFIX);
        }
        if (mappingConfig.getMapperSuffix() == null) {
            mappingConfig.setMapperSuffix(MappingConfigConstants.DEFAULT_MAPPER_SUFFIX);
        }
        if (mappingConfig.getGeneratedLanguage() == null) {
            mappingConfig.setGeneratedLanguage(MappingConfigConstants.DEFAULT_GENERATED_LANGUAGE);
        }
    }

    public void generate(EntityDefinition definition) {
        MappingContext context = MappingContext.builder()
                .setMappingConfig(mappingConfig)
                .build();
        for (FilesGenerator generator : FilesGeneratorFactory.getAll()) {
            generator.generate(templateFilesCreator, context, definition);
        }
    }

}
