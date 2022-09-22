package com.kurly.tet.guide.springrestdocs.infrastructure.web.dto;

import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PageResponseTest {
    @DisplayName("총갯수 계산")
    @MethodSource("caseCalculateTotal")
    @ParameterizedTest
    void test(List<String> content, PageRequest pageRequest, long total, long expectTotalCount) {
        var totalCount = Optional.of(pageRequest)
                .filter(it -> !content.isEmpty())
                .filter(it -> pageRequest.getOffset() + it.getSize() > total)
                .map(it -> pageRequest.getOffset() + content.size())
                .orElse(total);

        assertThat(totalCount).isEqualTo(expectTotalCount);
    }

    private static Stream<Arguments> caseCalculateTotal() {
        return Stream.of(
                Arguments.of(Collections.emptyList(), PageRequest.of(0, 10), 0L, 0L),
                Arguments.of(List.of("1"), PageRequest.of(0, 10), 1L, 1L),
                Arguments.of(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9"), PageRequest.of(0, 10), 9L, 9L),
                Arguments.of(List.of("11"), PageRequest.of(1, 10), 11L, 11L),
                Arguments.of(List.of("11", "12"), PageRequest.of(1, 10), 12L, 12L)
        );
    }
}