package de.qaware.openapigeneratorforspring.common.mapper;

import de.qaware.openapigeneratorforspring.model.info.Contact;
import lombok.RequiredArgsConstructor;

import static de.qaware.openapigeneratorforspring.common.util.OpenApiMapUtils.setMapIfNotEmpty;
import static de.qaware.openapigeneratorforspring.common.util.OpenApiStringUtils.setStringIfNotBlank;

@RequiredArgsConstructor
public class DefaultContactAnnotationMapper implements ContactAnnotationMapper {
    private final ExtensionAnnotationMapper extensionAnnotationMapper;

    @Override
    public Contact map(io.swagger.v3.oas.annotations.info.Contact contactAnnotation) {
        Contact contact = new Contact();
        setStringIfNotBlank(contactAnnotation.name(), contact::setName);
        setStringIfNotBlank(contactAnnotation.url(), contact::setUrl);
        setStringIfNotBlank(contactAnnotation.email(), contact::setEmail);
        setMapIfNotEmpty(extensionAnnotationMapper.mapArray(contactAnnotation.extensions()), contact::setExtensions);
        return contact;
    }
}
