package de.qaware.openapigeneratorforspring.autoconfigure;

import de.qaware.openapigeneratorforspring.common.mapper.TagAnnotationMapper;
import de.qaware.openapigeneratorforspring.common.tags.TagsSupportFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class OpenApiGeneratorTagsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TagsSupportFactory tagsSupportFactory(TagAnnotationMapper tagAnnotationMapper) {
        return new TagsSupportFactory(tagAnnotationMapper);
    }
}
