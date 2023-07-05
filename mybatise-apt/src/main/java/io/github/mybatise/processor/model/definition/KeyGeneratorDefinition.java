package io.github.mybatise.processor.model.definition;


import io.github.mybatise.annotation.GenerationType;

/**
 * @author Wes Lin
 */
public class KeyGeneratorDefinition {

    private boolean executeBefore;
    private String keyStatement;

    private GenerationType generationType = GenerationType.IDENTITY;
    private String identityGeneratorType;

    public void setExecuteBefore(boolean executeBefore) {
        this.executeBefore = executeBefore;
    }

    public void setKeyStatement(String keyStatement) {
        this.keyStatement = keyStatement;
    }

    public void setGenerationType(GenerationType generationType) {
        this.generationType = generationType;
    }

    public boolean isExecuteBefore() {
        return executeBefore;
    }

    public String getKeyStatement() {
        return keyStatement;
    }

    public GenerationType getGenerationType() {
        return generationType;
    }

    public String getIdentityGeneratorType() {
        return identityGeneratorType;
    }

    public void setIdentityGeneratorType(String identityGeneratorType) {
        this.identityGeneratorType = identityGeneratorType;
    }
}
