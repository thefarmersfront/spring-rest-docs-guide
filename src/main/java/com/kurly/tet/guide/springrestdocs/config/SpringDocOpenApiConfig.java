package com.kurly.tet.guide.springrestdocs.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * springdoc-opeanpi 를 통해 생성되는 Swagger 정의
 *
 * @see <a href="https://swagger.io/specification/">OpenAPI Specification - v3.0.3</a>
 * @see <a href="https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#openapi-annotations">OpenAPI Annotations</a>
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Spring REST Docs 가이드(Swagger 구성) 응?",
                version = "v2022.09.26",
                description = """
                        Spring REST Docs 가이드를 작성하려고 했는데, 일이 너무 커저버린 건에 대해서 반성중....
                        생각외로 간단하게 적용가능해서 놀람""",
                contact = @Contact(url = "https://helloworld.kurly.com/", name = "김지헌(팀 엔지니어링 팀)", email = "jiheon.kim@kurlycorp.com")
        )
)
@Configuration
public class SpringDocOpenApiConfig {
}
