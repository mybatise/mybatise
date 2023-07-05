package io.github.mybatise.processor.model;

/**
 * @author Wes Lin
 */
public interface MybatiseConfiguration {

    String getPackageName();

    String getSupportSuffix();

    String getMapperSuffix();

    GeneratedLanguage getGeneratedLanguage();

}
