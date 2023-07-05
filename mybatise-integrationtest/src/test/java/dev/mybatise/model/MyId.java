package dev.mybatise.model;

import io.github.mybatise.annotation.generators.IdentityGenerator;

import java.io.Serializable;

/**
 * @author Wes Lin
 */
public class MyId implements IdentityGenerator {
    @Override
    public Serializable generate() {
        return "1234";
    }
}
