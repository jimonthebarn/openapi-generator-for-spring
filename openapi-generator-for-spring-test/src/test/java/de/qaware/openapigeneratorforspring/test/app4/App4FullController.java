package de.qaware.openapigeneratorforspring.test.app4;

import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class App4FullController {

    @GetMapping("/get3")
    @ApiResponse(
            responseCode = "200",
            description = "All ok",
            headers = {
                    @Header(name = "header1", description = "Description header 1"),
                    @Header(name = "header2", description = "Description header 2",
                            required = true, deprecated = true,
                            schema = @Schema(implementation = String.class)
                    )
            },
            links = {
                    @Link(name = "link1", operationId = "getMapping3",
                            description = "link 1 description",
                            requestBody = "{\n" +
                                    "  \"keyInBody1\": \"value1\"\n" +
                                    "}",
                            parameters = {
                                    @LinkParameter(name = "parameter1", expression = "parameterExpression1")
                            },
                            server = @Server(url = "http://link-server1", description = "Server for Link 1",
                                    variables = {
                                            @ServerVariable(name = "server-variable-1", defaultValue = "value1", allowableValues = {"value1", "value2"},
                                                    description = "Server variable 1",
                                                    extensions = {
                                                            @Extension(name = "extension-server-variable-1", properties = @ExtensionProperty(name = "name1", value = "value2")),
                                                            @Extension(name = "extension-server-variable-2", properties = {
                                                                    @ExtensionProperty(name = "name1", value = "{\n" +
                                                                            "  \"key1\": \"value1\",\n" +
                                                                            "  \"key2\": \"value2\"\n" +
                                                                            "}", parseValue = true),
                                                            })
                                                    })
                                    }
                            )
                    ),
                    @Link(name = "link2", description = "link 2")
            },
            content = {
                    @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "Example1", summary = "summary example1", value = "some example value 1",
                                            extensions = @Extension(name = "extension-example-object-1", properties = @ExtensionProperty(name = "name1", value = "value2")),
                                            description = "example description 1"
                                    ),
                                    @ExampleObject(name = "Example2", summary = "summary example2", externalValue = "http://some-url1"),
                                    @ExampleObject(name = "Example3", summary = "summary example3", externalValue = "http://some-url2")
                            },
                            schema = @Schema(name = "schema-example-object-1", title = "schema title 1", implementation = String.class),
                            encoding = {
                                    @Encoding(name = "propertyInSchema1", contentType = "application/json", style = "form", explode = true, allowReserved = true),
                                    @Encoding(
                                            name = "propertyInSchema2", contentType = "text/plain", style = "form", explode = true, allowReserved = true,
                                            headers = @Header(name = "header3", description = "Description header 3"),
                                            extensions = @Extension(name = "extension-encoding-1", properties = @ExtensionProperty(name = "name1", value = "value2"))
                                    )
                            },
                            extensions = @Extension(name = "extension-content-1", properties = @ExtensionProperty(name = "name1", value = "value2"))
                    ),
                    @Content(mediaType = "text/plain", examples = {
                            @ExampleObject(name = "MyExampleName", summary = "summary example2", externalValue = "http://some-url1"),
                            @ExampleObject(name = "MyExampleName1", summary = "summary example3", externalValue = "http://some-url2")
                    }
                    )
            },
            extensions = {
                    @Extension(name = "extension-api-response-1", properties = @ExtensionProperty(name = "name1", value = "value2")),
                    @Extension(properties = {
                            @ExtensionProperty(name = "nameless-property-1", value = "value2"),
                            @ExtensionProperty(name = "nameless-property-2", value = "{\n" +
                                    "  \"key1\": \"value1\",\n" +
                                    "  \"key2\": \"value2\"\n" +
                                    "}", parseValue = true)
                    })
            }
    )
    public String getMapping3() {
        return null;
    }

    @GetMapping("/get4")
    @ApiResponse(
            headers = {
                    @Header(name = "header1", description = "Description header 1"),
                    @Header(name = "header2", description = "Description header 2",
                            required = true,
                            schema = @Schema(implementation = String.class)
                    )
            },
            content = {
                    @Content(mediaType = "application/json",
                            encoding = {
                                    @Encoding(
                                            name = "propertyInSchema2", contentType = "text/plain", style = "form", explode = true, allowReserved = true,
                                            headers = @Header(name = "header3", description = "Description header 3")
                                    )
                            }
                    )
            },
            links = @Link(name = "link2", description = "link 2")
    )
    public String getMapping4() {
        return null;
    }
}
