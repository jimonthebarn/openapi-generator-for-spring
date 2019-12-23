package de.qaware.openapigeneratorforspring.common.mapper;

import io.swagger.v3.oas.annotations.links.LinkParameter;

import java.util.Map;

import static de.qaware.openapigeneratorforspring.common.util.OpenApiMapUtils.buildMapFromArray;

public class DefaultLinkParameterAnnotationMapper implements LinkParameterAnnotationMapper {

    @Override
    public Map<String, String> mapArray(LinkParameter[] linkParameterAnnotations) {
        return buildMapFromArray(
                linkParameterAnnotations,
                LinkParameter::name,
                this::map
        );
    }

    @Override
    public String map(LinkParameter linkAnnotation) {
        return linkAnnotation.expression();
    }
}