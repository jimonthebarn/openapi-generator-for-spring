package de.qaware.openapigeneratorforspring.common.mapper;


import de.qaware.openapigeneratorforspring.model.info.Info;

@FunctionalInterface
public interface InfoAnnotationMapper {
    Info map(io.swagger.v3.oas.annotations.info.Info infoAnnotation);
}
