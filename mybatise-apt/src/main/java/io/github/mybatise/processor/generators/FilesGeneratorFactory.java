package io.github.mybatise.processor.generators;

import io.github.mybatise.processor.generators.imp.MapperGenerator;
import io.github.mybatise.processor.generators.imp.SupportGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wes Lin
 */
public class FilesGeneratorFactory {

    private FilesGeneratorFactory() {
    }

    public static List<FilesGenerator> getAll() {
        List<FilesGenerator> generators = new ArrayList<>();
        generators.add(new SupportGenerator());
        generators.add(new MapperGenerator());
        return generators;
    }

}
