package com.kurly.tet.guide.springrestdocs.documenation;

import org.springframework.restdocs.snippet.Attributes;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.restdocs.snippet.Attributes.key;

public class DocumentFormatGenerator {
    public DocumentFormatGenerator() {
        throw new UnsupportedOperationException("Util class.");
    }

    public static Attributes.Attribute required() {
        return key("required").value("true");
    }

    public static Attributes.Attribute optional() {
        return key("required").value("false");
    }

    public static Attributes.Attribute customFormat(String format) {
        return key("format").value(format);
    }

    public static Attributes.Attribute emptyFormat() {
        return customFormat("");
    }

    public static Attributes.Attribute dateTimeFormat() {
        return key("format").value("yyyy-MM-dd HH:mm:ss");
    }

    public static Attributes.Attribute dateFormat() {
        return key("format").value("yyyy-MM-dd");
    }

    public static Attributes.Attribute timeFormat() {
        return key("format").value("HH:mm:ss");
    }

    /**
     * Enum 타입 문자열 출력
     *
     * @param enumClass enum클래스
     * @param <E>       대상타입
     * @return "* `A`"
     */
    public static <E extends Enum<E>> String generatedEnums(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(el -> "* `" + el.name() + "`")
                .collect(Collectors.joining("\n"));
    }

    /**
     * Enum 타입 문자열 출력
     *
     * @param enumClass enum클래스
     * @param detailFun 문자열 출력 함수 (ex: Enum::getDescription)
     * @param <E>       변환하고자 하는 Enum 타입
     * @return "A(a 설명),\n B(b 설명)"
     */
    public static <E extends Enum<E>> Attributes.Attribute generateEnumAttrs(Class<E> enumClass, Function<E, String> detailFun) {
        var value = Arrays.stream(enumClass.getEnumConstants())
                .map(el -> "* `" + el.name() + "`(" + detailFun.apply(el) + ")")
                .collect(Collectors.joining("\n"));
        return key("format").value(value);
    }

    /**
     * Enum 타입 중 일부만 목록으로 노출하고 싶을 떄 사용
     *
     * @param enumClassList 대상 enum클래스 목록
     * @param detailFun     문자열 출력함수
     * @param <E>           변환하고자 하는 enum 타입
     * @return "* `A`(a 설명),\n* `B`(b 설명)"
     */
    public static <E extends Enum<E>> Attributes.Attribute generateEnumListFormatAttribute(List<E> enumClassList, Function<E, String> detailFun) {
        var value = enumClassList.stream()
                .map(el -> "* `" + el.name() + "`(" + detailFun.apply(el) + ")")
                .collect(Collectors.joining("\n"));

        return key("format").value(value);
    }
}
