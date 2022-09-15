package com.kurly.tet.guide.springrestdocs.common.util;

import com.kurly.tet.guide.springrestdocs.config.Constant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.kurly.tet.guide.springrestdocs.config.Constant.FORMAT_LOCAL_DATE_TIME;
import static java.util.Objects.isNull;

public class LocalDateTimeUtils {
    private LocalDateTimeUtils() {
        throw new UnsupportedOperationException("Utility class.");
    }

    /**
     * LocalDateTime 변환
     *
     * @param source 변환문자열
     * @return yyyy-MM-dd'T'HH:mm:ss 형식 {@link LocalDateTime}
     */
    public static LocalDateTime toLocalDateTime(String source) {
        return toLocalDateTime(source, FORMAT_LOCAL_DATE_TIME);
    }

    /**
     * LocalDateTime 변환
     *
     * @param source         변환문자열
     * @param dateTimeFormat pattern
     * @return pattern 에 맞춰 변환된 {@link LocalDateTime}
     */
    public static LocalDateTime toLocalDateTime(String source, String dateTimeFormat) {
        if (isNull(source) || source.isEmpty()) {
            return null;
        }

        return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(dateTimeFormat));
    }

    /**
     * LocalDateTime to 문자열 변환
     *
     * @param source 원천
     * @return "yyyy-MM-dd'T'HH:mm:ss" 형식으로 변환된 문자열
     */
    public static String toString(LocalDateTime source) {
        return toString(source, FORMAT_LOCAL_DATE_TIME);
    }

    /**
     * LocalDateTime to 문자열 변환
     *
     * @param source         원천
     * @param dateTimeFormat pattern
     * @return pattern 형식으로 변환된 문자열
     */
    public static String toString(LocalDateTime source, String dateTimeFormat) {
        if (isNull(source)) {
            return null;
        }

        return source.format(DateTimeFormatter.ofPattern(dateTimeFormat));
    }

    /**
     * 해당일 시작시
     *
     * @param source 원천일
     * @return 원천일 00:00:00
     */
    public static LocalDateTime ofFirst(LocalDate source) {
        return LocalDateTime.of(source, LocalTime.MIN);
    }

    /**
     * 해당일 종료시
     *
     * @param source 원천일
     * @return 원천일 23:59:59.999999999
     */
    public static LocalDateTime ofLast(LocalDate source) {
        return LocalDateTime.of(source, LocalTime.MAX);
    }

    /**
     * 해당월 시작일 시작시
     *
     * @param source 원천일
     * @return month-01 00:00:00
     */
    public static LocalDateTime ofMonthFirstDateTime(LocalDate source) {
        return ofFirst(LocalDateUtils.ofMonthFirstDay(source));
    }

    /**
     * 해당월 마지막날 마지막시
     *
     * @param source 원천일
     * @return month-lengthOfMonth 23:59:59.999999999
     */
    public static LocalDateTime ofMonthLastDateTime(LocalDate source) {
        return ofLast(LocalDateUtils.ofMonthLastDay(source));
    }

    /**
     * localDateTime을 Asia/Seoul ZoneDateTime 문자열로 변환합니다.
     *
     * @return Asia/Seoul ZoneDateTime 문자열
     */
    public static String convertZoneDateTimeFormat(final LocalDateTime source) {
        if (isNull(source)) {
            return null;
        }

        return source.atZone(Constant.DEFAULT_ZONE_ID)
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
