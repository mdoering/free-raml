package de.mdoering.raml.freemarker;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import org.raml.v2.api.model.v10.system.types.AnnotableStringType;
import org.raml.v2.api.model.v10.system.types.FullUriTemplateString;
import org.raml.v2.api.model.v10.system.types.MarkdownString;
import org.raml.v2.api.model.v10.system.types.StringType;

/**
 * Extended default object wrapper that includes template models for the types generated by the RAML parser.
 * This includes the markdown rendering template model.
 */
public class RamlObjectWrapper extends DefaultObjectWrapper {

    @Override
    protected TemplateModel handleUnknownType(Object obj) throws TemplateModelException {
        if (obj instanceof FullUriTemplateString) {
            return new BaseUriTypeTemplateModel((FullUriTemplateString)obj);
        }
        if (obj instanceof MarkdownString) {
            return new MarkdownTemplateModel((MarkdownString)obj);
        }
        if (obj instanceof AnnotableStringType) {
            return new AnnotableStringTemplateModel((AnnotableStringType)obj);
        }
        if (obj instanceof StringType) {
            return new StringTypeTemplateModel((StringType)obj);
        }

        return super.handleUnknownType(obj);
    }
}
