package io.github.mybatise.processor.generators;

import io.github.mybatise.processor.generators.velocity.CapitalizeDirective;
import io.github.mybatise.processor.model.GeneratedLanguage;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.util.EnumMap;

/**
 * @author Wes Lin
 */
public class TemplateRegistry {

    private TemplateRegistry() {
    }

    private static final VelocityEngine VE;

    private static final EnumMap<GeneratedLanguage, EnumMap<TemplateType, Template>> TEMPLATE_MAP =
            new EnumMap<>(GeneratedLanguage.class);

    static {
        VE = new VelocityEngine();
        VE.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        VE.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        VE.setProperty("userdirective", CapitalizeDirective.class.getName());
        VE.init();

        for (GeneratedLanguage language : GeneratedLanguage.values()) {
            TEMPLATE_MAP.put(language, getTemplates(language));
        }
    }

    public static Template getTemplateWithLang(GeneratedLanguage generatedLanguage,
                                               TemplateType templateType) {
        return TEMPLATE_MAP.get(generatedLanguage).get(templateType);
    }

    private static EnumMap<TemplateType, Template> getTemplates(GeneratedLanguage language) {
        EnumMap<TemplateType, Template> templates = new EnumMap<>(TemplateType.class);
        for (TemplateType type : TemplateType.values()) {
            templates.put(type, VE.getTemplate(buildTemplatePath(type, language)));
        }
        return templates;
    }

    private static String buildTemplatePath(TemplateType type, GeneratedLanguage language) {
        return String.format("templates/%s-lang/%s.vm", language.name().toLowerCase(), type.name().toLowerCase());
    }
}
