package de.qaware.openapigeneratorforspring.common.annotation;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.function.Predicate;
import java.util.stream.Stream;

@FunctionalInterface
public interface AnnotationsSupplier {
    /**
     * Supply annotations for entity under investigation. Annotations
     * with highest precedence should appear first in returned stream.
     *
     * @param annotationType type of annotations to find
     * @param <A>            annotation class type
     * @return stream of found annotation of given type
     */
    <A extends Annotation> Stream<A> findAnnotations(Class<A> annotationType);

    @Nullable
    default <A extends Annotation> A findFirstAnnotation(Class<A> annotationType) {
        return findAnnotations(annotationType)
                .findFirst()
                .orElse(null);
    }

    default AnnotationsSupplier withExcludedBy(Predicate<? super Annotation> annotationExclusion) {
        AnnotationsSupplier annotationsSupplier = this;
        return new AnnotationsSupplier() {
            @Override
            public <A extends Annotation> Stream<A> findAnnotations(Class<A> annotationType) {
                return annotationsSupplier.findAnnotations(annotationType).filter(annotationExclusion.negate());
            }
        };
    }

    default AnnotationsSupplier andThen(AnnotationsSupplier anotherAnnotationsSupplier) {
        AnnotationsSupplier annotationsSupplier = this;
        return new AnnotationsSupplier() {
            @Override
            public <A extends Annotation> Stream<A> findAnnotations(Class<A> annotationType) {
                return Stream.concat(annotationsSupplier.findAnnotations(annotationType), anotherAnnotationsSupplier.findAnnotations(annotationType));
            }
        };
    }
}