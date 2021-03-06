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

package de.qaware.openapigeneratorforspring.common.operation.customizer;

import de.qaware.openapigeneratorforspring.common.operation.OperationBuilderContext;
import de.qaware.openapigeneratorforspring.common.operation.id.OperationIdProvider;
import de.qaware.openapigeneratorforspring.model.operation.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultOperationIdCustomizer implements OperationCustomizer {

    public static final int ORDER = DEFAULT_ORDER;

    private final OperationIdProvider operationIdProvider;

    @Override
    public void customize(Operation operation, OperationBuilderContext operationBuilderContext) {
        if (operation.getOperationId() == null) {
            operation.setOperationId(operationIdProvider.getOperationId(operationBuilderContext.getOperationInfo()));
        }
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
