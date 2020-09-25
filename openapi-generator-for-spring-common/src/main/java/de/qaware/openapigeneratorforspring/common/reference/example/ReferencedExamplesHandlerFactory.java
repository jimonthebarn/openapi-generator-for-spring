package de.qaware.openapigeneratorforspring.common.reference.example;

import de.qaware.openapigeneratorforspring.common.mapper.ExampleObjectAnnotationMapper;
import de.qaware.openapigeneratorforspring.common.reference.fortype.ReferencedItemHandler;
import de.qaware.openapigeneratorforspring.common.reference.fortype.ReferencedItemHandlerFactory;
import io.swagger.v3.oas.models.examples.Example;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ResolvableType;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ReferencedExamplesHandlerFactory implements ReferencedItemHandlerFactory<List<ExampleObjectAnnotationMapper.ExampleWithOptionalName>, Map<String, Example>> {
    private final ReferenceDeciderForExample referenceDecider;
    private final ReferenceNameFactoryForExample referenceNameFactory;
    private final ReferenceNameConflictResolverForExample referenceNameConflictResolver;

    @Override
    public ReferencedItemHandler<List<ExampleObjectAnnotationMapper.ExampleWithOptionalName>, Map<String, Example>> create() {
        ReferencedExampleStorage storage = new ReferencedExampleStorage(referenceDecider, referenceNameFactory, referenceNameConflictResolver);
        return new ReferencedExamplesHandlerImpl(storage, referenceNameFactory);
    }

    @Override
    public ResolvableType getResolvableTypeOfItem() {
        return ResolvableType.forClassWithGenerics(List.class, ExampleObjectAnnotationMapper.ExampleWithOptionalName.class);
    }
}