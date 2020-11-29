/*-
 * #%L
 * OpenAPI Generator for Spring Boot :: UI
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

package de.qaware.openapigeneratorforspring.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

public interface OpenApiSwaggerUiApiDocsUrisSupplier {

    List<ApiDocsUriWithName> getApiDocsUris(URI apiDocsUri);

    @RequiredArgsConstructor(staticName = "of")
    @Getter
    class ApiDocsUriWithName {
        private final String name;
        private final URI apiDocsUri;
    }
}
