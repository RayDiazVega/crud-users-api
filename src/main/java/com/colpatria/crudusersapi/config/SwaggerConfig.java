package com.colpatria.crudusersapi.config;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import java.util.List;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Value("${info.project.version}")
  private String projectVersion;

  @Value("${info.project.name}")
  private String projectName;

  @Value("${info.project.description}")
  private String projectDescription;

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI().info(
        new Info().version(projectVersion).title(projectName).description(projectDescription)
            .contact(new Contact().name("Raymundo Diaz Vega").email("raydiazvega@gmail.com"))
            .license(new License().name("Apache 2.0").url("http://springdoc.org")));
  }

  @Bean
  public OpenApiCustomiser openApiCustomiser() {
    return openApi -> openApi.getPaths().values().parallelStream().forEach(pathItem ->
        pathItem.readOperations().parallelStream().forEach(operation -> {
          if (List.of("save", "update").contains(operation.getOperationId())) {
            operation.getResponses()
                .addApiResponse("400", new ApiResponse().description("Bad Request")
                    .content(new Content().addMediaType(APPLICATION_JSON_VALUE, new MediaType()
                        .example("{\n"
                            + "    \"timestamp\": \"2022-03-11T14:36:48.4514789\",\n"
                            + "    \"error\": [\n"
                            + "        \"names is required\"\n"
                            + "    ],\n"
                            + "    \"message\": \"Client error\"\n"
                            + "}"))));
          }
          if (List.of("findById", "findByEmail", "deleteById", "findAllByUserId", "findById_1",
              "deleteById_1").contains(operation.getOperationId())) {
            operation.getResponses()
                .addApiResponse("400", new ApiResponse().description("Bad Request")
                    .content(new Content().addMediaType(APPLICATION_JSON_VALUE, new MediaType()
                        .example("{\n"
                            + "  \"timestamp\": \"2022-05-26T09:33:15.5297727\",\n"
                            + "  \"message\": \"Client error\",\n"
                            + "  \"error\": \"No value present\"\n"
                            + "}"))));
          }
          if (List.of("save_1", "update_1").contains(operation.getOperationId())) {
            operation.getResponses()
                .addApiResponse("400", new ApiResponse().description("Bad Request")
                    .content(new Content().addMediaType(APPLICATION_JSON_VALUE, new MediaType()
                        .example("{\n"
                            + "    \"timestamp\": \"2022-03-11T14:36:48.4514789\",\n"
                            + "    \"error\": [\n"
                            + "        \"name is required\"\n"
                            + "    ],\n"
                            + "    \"message\": \"Client error\"\n"
                            + "}"))));
          }
          operation.getResponses()
              .addApiResponse("500", new ApiResponse().description("Internal Server Error")
                  .content(new Content().addMediaType(APPLICATION_JSON_VALUE, new MediaType()
                      .example("{\n"
                          + "    \"timestamp\": \"2022-03-11T14:36:48.4514789\",\n"
                          + "    \"message\": \"Server error\"\n"
                          + "}"))));
        }));
  }
}
