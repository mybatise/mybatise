package io.github.mybatise.processor.generators;

import io.github.mybatise.processor.model.MappingContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import static io.github.mybatise.processor.model.DataModelFields.CLASS_NAME;
import static io.github.mybatise.processor.model.DataModelFields.PACKAGE;

/**
 * @author Wes Lin
 */
public class TemplateFilesCreator {

    private final Filer filer;

    public TemplateFilesCreator(Filer filer) {
        this.filer = filer;
    }

    public void creat(MappingContext context, TemplateType templateType, Map<String, Object> data) {
        String fileName = (String) data.get(CLASS_NAME);
        String packName = (String) data.get(PACKAGE);
        try {
            JavaFileObject sourceFile = filer.createSourceFile(packName + "." + fileName);
            Template template = TemplateRegistry.getTemplateWithLang(context.getGeneratedLanguage(), templateType);
            try (Writer writer = sourceFile.openWriter()) {
                VelocityContext velocityContext = new VelocityContext(data);
                template.merge(velocityContext, writer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
