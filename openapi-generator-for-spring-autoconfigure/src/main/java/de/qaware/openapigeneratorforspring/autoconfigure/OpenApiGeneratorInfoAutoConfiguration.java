package de.qaware.openapigeneratorforspring.autoconfigure;

import de.qaware.openapigeneratorforspring.common.info.DefaultOpenApiInfoSupplier;
import de.qaware.openapigeneratorforspring.common.info.DefaultOpenApiVersionSupplier;
import de.qaware.openapigeneratorforspring.common.info.OpenApiInfoConfigurationProperties;
import de.qaware.openapigeneratorforspring.common.info.OpenApiInfoSupplier;
import de.qaware.openapigeneratorforspring.common.info.OpenApiVersionSupplier;
import de.qaware.openapigeneratorforspring.common.mapper.InfoAnnotationMapper;
import de.qaware.openapigeneratorforspring.common.supplier.OpenAPIDefinitionAnnotationSupplier;
import de.qaware.openapigeneratorforspring.common.supplier.OpenApiSpringBootApplicationClassSupplier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(OpenApiInfoConfigurationProperties.class)
public class OpenApiGeneratorInfoAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OpenApiInfoSupplier defaultOpenApiInfoSupplier(
            OpenApiInfoConfigurationProperties infoProperties,
            InfoAnnotationMapper infoAnnotationMapper,
            OpenApiVersionSupplier openApiVersionSupplier,
            OpenApiSpringBootApplicationClassSupplier springBootApplicationClassSupplier,
            OpenAPIDefinitionAnnotationSupplier openAPIDefinitionAnnotationSupplier
    ) {
        return new DefaultOpenApiInfoSupplier(infoProperties, infoAnnotationMapper, openApiVersionSupplier,
                springBootApplicationClassSupplier, openAPIDefinitionAnnotationSupplier);
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenApiVersionSupplier defaultOpenApiVersionSupplier() {
        return new DefaultOpenApiVersionSupplier();
    }

}
