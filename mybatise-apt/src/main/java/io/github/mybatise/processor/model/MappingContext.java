package io.github.mybatise.processor.model;

/**
 * @author Wes Lin
 */
public class MappingContext implements MybatiseConfiguration {

    private final MappingConfig config;

    private MappingContext(MappingConfig config) {
        this.config = config;
    }

    @Override
    public String getPackageName() {
        return config.getPackageName();
    }

    @Override
    public String getSupportSuffix() {
        return config.getSupportSuffix();
    }

    @Override
    public String getMapperSuffix() {
        return config.getMapperSuffix();
    }

    @Override
    public GeneratedLanguage getGeneratedLanguage() {
        return config.getGeneratedLanguage();
    }

    public static MappingContext.Builder builder() {
        return new MappingContext.Builder();
    }

    public static class Builder {

        private MappingConfig mappingConfig;

        public Builder setMappingConfig(MappingConfig mappingConfig) {
            this.mappingConfig = mappingConfig;
            return this;
        }

        public MappingContext build() {
            return new MappingContext(mappingConfig);
        }
    }
}
