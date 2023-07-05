package io.github.mybatise.processor.model;

/**
 * @author Wes Lin
 */
public class MappingConfig implements MybatiseConfiguration {

    private String packageName;
    private String supportSuffix;
    private String mapperSuffix;
    private GeneratedLanguage generatedLanguage;

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSupportSuffix() {
        return supportSuffix;
    }

    public void setSupportSuffix(String supportSuffix) {
        this.supportSuffix = supportSuffix;
    }

    @Override
    public GeneratedLanguage getGeneratedLanguage() {
        return generatedLanguage;
    }

    public void setGeneratedLanguage(GeneratedLanguage generatedLanguage) {
        this.generatedLanguage = generatedLanguage;
    }

    @Override
    public String getMapperSuffix() {
        return mapperSuffix;
    }

    public void setMapperSuffix(String mapperSuffix) {
        this.mapperSuffix = mapperSuffix;
    }
}
