package de.qaware.openapigeneratorforspring.common.reference.component.response;

import de.qaware.openapigeneratorforspring.model.response.ApiResponse;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultReferenceIdentifierFactoryForApiResponse implements ReferenceIdentifierFactoryForApiResponse {

    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public List<Object> buildIdentifierComponents(ApiResponse item, @Nullable String suggestedIdentifier) {
        return StringUtils.isNotBlank(suggestedIdentifier) ? Collections.singletonList(suggestedIdentifier)
                : Arrays.asList("ApiResponse", counter.getAndIncrement());
    }
}