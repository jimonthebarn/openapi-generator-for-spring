package de.qaware.openapigeneratorforspring.common;

import de.qaware.openapigeneratorforspring.common.filter.operation.OperationFilter;
import de.qaware.openapigeneratorforspring.common.filter.pathitem.PathItemFilter;
import de.qaware.openapigeneratorforspring.common.operation.OperationBuilder;
import de.qaware.openapigeneratorforspring.common.operation.OperationBuilderContext;
import de.qaware.openapigeneratorforspring.common.operation.id.OperationIdConflict;
import de.qaware.openapigeneratorforspring.common.operation.id.OperationIdConflictResolver;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class OpenApiGenerator {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    private final OperationBuilder operationBuilder;
    private final List<PathItemFilter> pathItemFilters;
    private final List<OperationFilter> operationFilters;
    private final OperationIdConflictResolver operationIdConflictResolver;

    public OpenAPI generateOpenApi() {

        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        Paths paths = new Paths();
        MultiValueMap<String, OperationIdConflict> operationIdConflicts = new LinkedMultiValueMap<>();
        map.forEach((info, handlerMethod) -> info.getPatternsCondition().getPatterns().forEach(pathPattern -> {
            PathItem pathItem = new PathItem();
            Set<RequestMethod> requestMethods = info.getMethodsCondition().getMethods();
            Map<RequestMethod, Operation> operationPerMethod = new EnumMap<>(RequestMethod.class);
            MultiValueMap<String, OperationIdConflict> operationIdConflictsPerPathItem = new LinkedMultiValueMap<>();
            requestMethods.forEach(requestMethod -> {
                OperationBuilderContext operationBuilderContext = new OperationBuilderContext(requestMethod, pathPattern, handlerMethod);
                Operation operation = operationBuilder.buildOperation(operationBuilderContext);
                if (isAcceptedByAllOperationFilters(operation, handlerMethod)) {
                    String operationId = operation.getOperationId();
                    if (operationId != null) {
                        operationIdConflictsPerPathItem.add(operationId, OperationIdConflict.of(operation, operationBuilderContext));
                    }
                    operationPerMethod.put(requestMethod, operation);
                    setOperationOnPathItem(requestMethod, pathItem, operation);
                }
            });

            if (isAcceptedByAllPathFilters(pathItem, pathPattern, operationPerMethod)) {
                operationIdConflicts.addAll(operationIdConflictsPerPathItem);
                paths.addPathItem(pathPattern, pathItem);
            }
        }));

        operationIdConflicts.forEach((operationId, operationIdConflictList) -> {
            if (operationIdConflictList.size() > 1) {
                operationIdConflictResolver.resolveConflict(operationId, operationIdConflictList);
            }
        });

        OpenAPI openApi = new OpenAPI();
        if (!paths.isEmpty()) {
            openApi.setPaths(paths);
        }
        return openApi;
    }

    private boolean isAcceptedByAllOperationFilters(Operation operation, HandlerMethod handlerMethod) {
        for (OperationFilter operationFilter : operationFilters) {
            if (!operationFilter.accept(operation, handlerMethod)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAcceptedByAllPathFilters(PathItem pathItem, String pathPattern, Map<RequestMethod, Operation> operationPerMethod) {
        for (PathItemFilter pathItemFilter : pathItemFilters) {
            if (!pathItemFilter.accept(pathItem, pathPattern, operationPerMethod)) {
                return false;
            }
        }
        return true;
    }

    private static void setOperationOnPathItem(RequestMethod requestMethod, PathItem pathItem, Operation operation) {
        switch (requestMethod) {
            case GET:
                pathItem.setGet(operation);
                break;
            case HEAD:
                pathItem.setHead(operation);
                break;
            case POST:
                pathItem.setPost(operation);
                break;
            case PUT:
                pathItem.setPut(operation);
                break;
            case PATCH:
                pathItem.setPatch(operation);
                break;
            case DELETE:
                pathItem.setDelete(operation);
                break;
            case OPTIONS:
                pathItem.setOptions(operation);
                break;
            case TRACE:
                pathItem.setTrace(operation);
                break;
        }
    }

}