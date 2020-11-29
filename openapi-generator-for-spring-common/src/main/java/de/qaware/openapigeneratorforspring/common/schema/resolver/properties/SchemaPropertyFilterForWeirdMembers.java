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

package de.qaware.openapigeneratorforspring.common.schema.resolver.properties;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static de.qaware.openapigeneratorforspring.common.util.OpenApiOrderedUtils.earlierThan;

public class SchemaPropertyFilterForWeirdMembers implements SchemaPropertyFilter {
    // Run a little bit earlier to get rid of weird properties early
    public static final int ORDER = earlierThan(DEFAULT_ORDER);

    @Override
    public boolean accept(BeanPropertyDefinition property, BeanDescription beanDescriptionForType) {
        // safe-guard weird properties
        return Objects.nonNull(property.getAccessor()) && StringUtils.isNotBlank(property.getName());
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
