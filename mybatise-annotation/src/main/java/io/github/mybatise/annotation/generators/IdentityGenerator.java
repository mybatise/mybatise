package io.github.mybatise.annotation.generators;

import java.io.Serializable;

/**
 * @author Wes Lin
 */
public interface IdentityGenerator {

    Serializable generate();

}