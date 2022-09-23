package com.kurly.tet.guide.springrestdocs.documenation;

import com.kurly.tet.guide.springrestdocs.domain.product.ProductStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kurly.tet.guide.springrestdocs.domain.product.ProductStatus.ACTIVATED;
import static com.kurly.tet.guide.springrestdocs.domain.product.ProductStatus.CREATED;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * enum 타입 아스키독 목록 스니펫 사용 문자형식 생성
 */
@DisplayName("지정된 enum 타입을 아스키독문법 목록(<li>) 형태로 출력")
class DocumentFormatGeneratorTest {
    @DisplayName("속성값만 나열함")
    @Test
    void testGeneratedEnums() {
        var result = DocumentFormatGenerator.generatedEnums(ProductStatus.class);

        assertThat(result).isEqualTo("""
                * `CREATED`
                * `ACTIVATED`
                * `ARCHIVED`""");
    }

    @DisplayName("속성값(설명) 나열함")
    @Test
    void testGeneratedEnumAttr() {
        var attrs = DocumentFormatGenerator.generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription);

        assertThat(attrs.getValue()).isEqualTo("""
                * `CREATED`(생성)
                * `ACTIVATED`(활성화)
                * `ARCHIVED`(보관처리됨)""");
    }

    @DisplayName("지정된 속성값(설명) 나열함")
    @Test
    void testGeneratedEnumListFormat() {
        var failed = List.of(CREATED, ACTIVATED);

        var formatAttr = DocumentFormatGenerator.generateEnumListFormatAttribute(failed, ProductStatus::getDescription);

        assertThat(formatAttr.getValue()).isEqualTo(
                """
                * `CREATED`(생성)
                * `ACTIVATED`(활성화)""");
    }
}