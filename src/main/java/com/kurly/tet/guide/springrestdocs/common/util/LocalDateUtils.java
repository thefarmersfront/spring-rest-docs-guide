package com.kurly.tet.guide.springrestdocs.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.kurly.tet.guide.springrestdocs.config.Constant.FORMAT_LOCAL_DATE;
import static java.util.Objects.isNull;

public class LocalDateUtils {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final int FIRST_DAY_OF_MONTH = 1;

    private LocalDateUtils() {
        throw new IllegalStateException("Util class.");
    }

    /**
     * {@link LocalDate} 변환
     *
     * @param source 원천 문자열
     * @return "yyyy-MM-dd" 형식으로 변환된 {@link LocalDate}
     */
    public static LocalDate toLocalDate(String source) {
        return toLocalDate(source, FORMAT_LOCAL_DATE);
    }

    /**
     * {@link LocalDate} 변환
     *
     * @param source     원천문자열
     * @param dateFormat 변환날짜형식
     * @return 지정형식으로 변환된 {@link LocalDate}
     */
    public static LocalDate toLocalDate(String source, String dateFormat) {
        if (isNull(source) || source.isEmpty()) {
            return null;
        }

        return LocalDate.parse(source, DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * 문자열 변환
     *
     * @param source 원천일
     * @return yyyy-MM-dd 형식 문자열
     */
    public static String toString(LocalDate source) {
        if (isNull(source)) {
            return null;
        }

        return source.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }

    /**
     * 문자열 변환
     *
     * @param source     원천일
     * @param dateFormat 변환날짜형식
     * @return '변환날짜형식'으로 변환된 문자열
     */
    public static String toString(LocalDate source, String dateFormat) {
        return source.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * 해당월 첫번째 날
     *
     * @param source 원천일
     * @return 1 of source
     */
    public static LocalDate ofMonthFirstDay(LocalDate source) {
        return source.withDayOfMonth(FIRST_DAY_OF_MONTH);
    }

    /**
     * 해당월 마지막 날
     *
     * @param source 원천일
     * @return lengthOfMonth of source
     */
    public static LocalDate ofMonthLastDay(LocalDate source) {
        return source.withDayOfMonth(source.lengthOfMonth());
    }
}
