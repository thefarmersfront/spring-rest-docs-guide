package com.kurly.tet.guide.springrestdocs.config;

import java.time.ZoneId;

/**
 * 애플리케이션에서 공통으로 사용되는 불변변수를 선언하는 목적으로 사용합니다.
 */
public class Constant {
    public static final String PROFILE_TEST = "test";
    public static final String PROFILE_LOCAL = "local";
    public static final String PROFILE_DEV = "dev";
    public static final String PROFILE_STAGE = "stg";
    public static final String PROFILE_PERFORMANCE = "perf";
    public static final String PROFILE_PRODUCTION = "prod";

    public static final String HOST_LOCAL = "12.0.0.1";
    public static final String HOST_DEV = ""; // 개발DNS 는 배포에 따라 맞춰 진행

    public static final String FORMAT_LOCAL_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FORMAT_LOCAL_DATE = "yyyy-MM-dd";
    public static final String FORMAT_LOCAL_TIME = "HH:mm:ss";

    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Seoul");

    private Constant() {
        throw new UnsupportedOperationException("불변변수용 클래스");
    }
}
