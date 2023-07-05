package io.github.mybatise.annotation.generators;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Wes Lin
 */
public final class UUIDHexGenerator implements IdentityGenerator {
    @Override
    public Serializable generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
