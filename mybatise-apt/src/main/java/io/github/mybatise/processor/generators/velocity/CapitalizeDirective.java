package io.github.mybatise.processor.generators.velocity;

import io.github.mybatise.processor.util.StringUtils;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Wes Lin
 */
public class CapitalizeDirective extends Directive {

    @Override
    public String getName() {
        return "capitalize";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node)
            throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        Node param = node.jjtGetChild(0);
        Object value = param.value(context);
        if (value != null && value instanceof CharSequence) {
            CharSequence str = (CharSequence) value;
            writer.write(StringUtils.capitalize(str.toString()));
        }
        return true;
    }
}
