package io.github.mybatise.core;

import io.github.mybatise.annotation.generators.IdentityGenerator;

/**
 * @author Wes Lin
 */
public class Column {

    private String name;
    private String property;
    private boolean id;
    private Class<? extends IdentityGenerator> identityGenerator;
    private String typeHandler;
    private String jdbcType;

    private Column() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public Column(Builder builder) {
        this.property = builder.property;
        this.name = builder.name;
        this.id = builder.id;
        this.identityGenerator = builder.identityGenerator;
        this.typeHandler = builder.typeHandler;
        this.jdbcType = builder.jdbcType;
    }

    public String getProps() {
        StringBuilder sb = new StringBuilder();
        if (typeHandler != null) {
            sb.append(", typeHandler=" + typeHandler);
        }
        if (jdbcType != null) {
            sb.append(",jdbcType=" + jdbcType);
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getProperty() {
        return property;
    }

    public boolean getId() {
        return id;
    }

    public Class<? extends IdentityGenerator> getIdentityGenerator() {
        return identityGenerator;
    }

    public String getTypeHandler() {
        return typeHandler;
    }

    public static class Builder {

        private String name;
        private String property;
        private boolean id;
        private Class<? extends IdentityGenerator> identityGenerator;
        private String typeHandler;
        private String jdbcType;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder property(String property) {
            this.property = property;
            return this;
        }

        public Builder id(boolean id) {
            this.id = id;
            return this;
        }

        public Builder identityGenerator(Class<? extends IdentityGenerator> identityGenerator) {
            this.identityGenerator = identityGenerator;
            return this;
        }

        public Builder typeHandler(String typeHandler) {
            this.typeHandler = typeHandler;
            return this;
        }

        public Builder jdbcType(String jdbcType){
            this.jdbcType = jdbcType;
            return this;
        }

        public Column build() {
            return new Column(this);
        }
    }
}
