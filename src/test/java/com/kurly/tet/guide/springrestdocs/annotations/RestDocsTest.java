package com.kurly.tet.guide.springrestdocs.annotations;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.restdocs.RestDocumentationExtension;

import java.lang.annotation.*;

/**
 * @see AutoConfigureMockMvc
 * @See AutoConfigureRestDocs
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Tag("restDocs")
@ExtendWith({MockitoExtension.class, RestDocumentationExtension.class})
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public @interface RestDocsTest {
}
