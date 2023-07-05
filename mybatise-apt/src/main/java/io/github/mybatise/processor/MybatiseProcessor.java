package io.github.mybatise.processor;

import io.github.mybatise.annotation.Column;
import io.github.mybatise.annotation.GeneratedValue;
import io.github.mybatise.annotation.Table;
import io.github.mybatise.processor.generators.TemplateFilesCreator;
import io.github.mybatise.processor.model.MappingConfig;
import io.github.mybatise.processor.model.definition.EntityDefinition;
import io.github.mybatise.processor.model.definition.FieldDefinition;
import io.github.mybatise.processor.model.definition.KeyGeneratorDefinition;
import org.apache.ibatis.type.JdbcType;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Wes Lin
 */
@SupportedOptions({
        MybatiseProcessor.PACKAGE_NAME
})
public class MybatiseProcessor extends AbstractProcessor {

    protected static final String PACKAGE_NAME = "mybatise.packageName";

    private MappingConfig config;
    private Map<String, List<FieldDefinition>> fieldDefMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        config = createConfig();
    }

    private String getType(Supplier<Class> classSupplier) {
        try {
            return classSupplier.get().getName();
        } catch (MirroredTypeException e) {
            String type = e.getTypeMirror().toString();
            return type;
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        initFieldDef(roundEnv);
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Table.class);
        List<EntityDefinition> entityDefinitions = elements.stream()
                .map(this::getEntityDefinition)
                .collect(Collectors.toList());
        Filer filer = processingEnv.getFiler();
        JavaCodegen codegen = new JavaCodegen(config, new TemplateFilesCreator(filer));
        for (EntityDefinition definition : entityDefinitions) {
            codegen.generate(definition);
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> s = new HashSet<>();
        s.add(Table.class.getName());
        s.add(Column.class.getName());
        return s;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    public MappingConfig createConfig() {
        Map<String, String> options = processingEnv.getOptions();
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.setPackageName(options.get(PACKAGE_NAME));
        return mappingConfig;
    }

    private void initFieldDef(RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Column.class);
        for (Element element : elements) {
            String owner = element.getEnclosingElement().toString();
            List<FieldDefinition> fieldDefinitions = fieldDefMap.getOrDefault(owner, new ArrayList<>());
            FieldDefinition fieldDefinition = new FieldDefinition();
            fieldDefinition.setName(element.getSimpleName().toString());
            fieldDefinition.setType(element.asType().toString());
            Column column = element.getAnnotation(Column.class);
            fieldDefinition.setColumn(column.name());
            fieldDefinition.setId(column.id());
            fieldDefinition.setTypeHandler(getType(() -> column.typeHandler()));
            fieldDefinition.setJdbcType(column.jdbcType());
            if (fieldDefinition.getId()) {
                KeyGeneratorDefinition keyGeneratorDefinition = new KeyGeneratorDefinition();
                GeneratedValue generatedValue = element.getAnnotation(GeneratedValue.class);
                if (generatedValue != null) {
                    keyGeneratorDefinition.setGenerationType(generatedValue.strategy());
                    keyGeneratorDefinition.setKeyStatement(generatedValue.keyStatement());
                    keyGeneratorDefinition.setExecuteBefore(generatedValue.before());
                    keyGeneratorDefinition.setIdentityGeneratorType(
                            getType(() -> generatedValue.generator())
                    );
                }
                fieldDefinition.setKeyGenerator(keyGeneratorDefinition);
            }
            fieldDefinitions.add(fieldDefinition);
            fieldDefMap.put(owner, fieldDefinitions);
        }
    }

    private EntityDefinition getEntityDefinition(Element element) {
        Table table = element.getAnnotation(Table.class);
        TypeElement typeElement = (TypeElement) element;
        String typeName = typeElement.toString();
        List<FieldDefinition> fieldDefs = fieldDefMap.get(typeName);
        String superTypeName = typeElement.getSuperclass().toString();
        if (!superTypeName.equals("java.lang.Object")) {
            List<FieldDefinition> otherFieldDefs = fieldDefMap.get(superTypeName);
            if (otherFieldDefs != null) {
                fieldDefs.addAll(otherFieldDefs);
            }
        }
        EntityDefinition entityDef = new EntityDefinition(
                table.name(),
                typeName,
                fieldDefs);
        return entityDef;
    }

}
