package io.github.mybatise.processor.generators;


import io.github.mybatise.processor.model.definition.FieldDefinition;
import io.github.mybatise.processor.model.GeneratedLanguage;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.junit.Test;

import java.io.StringWriter;

import static io.github.mybatise.processor.model.DataModelFields.PK_FIELD;

/**
 * @author Wes Lin
 */
public class TemplateRegistryTest {

    @Test
    public void test() {
        Template template = TemplateRegistry.getTemplateWithLang(GeneratedLanguage.JAVA, TemplateType.MAPPER);
        StringWriter writer = new StringWriter();
        VelocityContext context = new VelocityContext();
        FieldDefinition pkFile = new FieldDefinition();
        context.put(PK_FIELD, pkFile);
        template.merge(context, writer);
        System.out.println(writer);
    }

}