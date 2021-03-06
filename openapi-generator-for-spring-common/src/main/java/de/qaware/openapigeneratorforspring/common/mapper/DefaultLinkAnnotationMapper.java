/*-
 * #%L
 * OpenAPI Generator for Spring Boot :: Common
 * %%
 * Copyright (C) 2020 QAware GmbH
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package de.qaware.openapigeneratorforspring.common.mapper;

import de.qaware.openapigeneratorforspring.model.link.Link;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;

import static de.qaware.openapigeneratorforspring.common.util.OpenApiMapUtils.buildStringMapFromStream;
import static de.qaware.openapigeneratorforspring.common.util.OpenApiMapUtils.setMapIfNotEmpty;
import static de.qaware.openapigeneratorforspring.common.util.OpenApiObjectUtils.setIfNotEmpty;
import static de.qaware.openapigeneratorforspring.common.util.OpenApiStringUtils.setStringIfNotBlank;

@RequiredArgsConstructor
public class DefaultLinkAnnotationMapper implements LinkAnnotationMapper {

    private final ParsableValueMapper parsableValueMapper;
    private final ExtensionAnnotationMapper extensionAnnotationMapper;
    private final LinkParameterAnnotationMapper linkParameterAnnotationMapper;
    private final ServerAnnotationMapper serverAnnotationMapper;

    @Override
    public Map<String, Link> mapArray(io.swagger.v3.oas.annotations.links.Link[] linkAnnotations) {
        return buildStringMapFromStream(
                Arrays.stream(linkAnnotations),
                io.swagger.v3.oas.annotations.links.Link::name,
                this::map
        );
    }

    @Override
    public Link map(io.swagger.v3.oas.annotations.links.Link linkAnnotation) {
        Link link = new Link();
        setStringIfNotBlank(linkAnnotation.operationRef(), link::setOperationRef);
        setStringIfNotBlank(linkAnnotation.operationId(), link::setOperationId);
        setMapIfNotEmpty(linkParameterAnnotationMapper.mapArray(linkAnnotation.parameters()), link::setParameters);
        setStringIfNotBlank(linkAnnotation.requestBody(), body -> link.setRequestBody(parsableValueMapper.parse(body)));
        setStringIfNotBlank(linkAnnotation.description(), link::setDescription);
        setStringIfNotBlank(linkAnnotation.ref(), link::setRef);
        setMapIfNotEmpty(extensionAnnotationMapper.mapArray(linkAnnotation.extensions()), link::setExtensions);
        setIfNotEmpty(serverAnnotationMapper.map(linkAnnotation.server()), link::setServer);
        return link;
    }
}
