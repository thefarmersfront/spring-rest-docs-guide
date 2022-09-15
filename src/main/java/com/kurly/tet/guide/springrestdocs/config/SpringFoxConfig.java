package com.kurly.tet.guide.springrestdocs.config;

import org.springframework.context.annotation.Profile;

/**
 * Swagger 설정
 * <ul>
 *     <li>개발환경 이상에서는 활성화하지 않도록 제한</li>
 * </ul>
 */
@Profile({Constant.PROFILE_LOCAL, Constant.PROFILE_TEST, Constant.PROFILE_DEV, Constant.PROFILE_STAGE})
//@EnableSwagger2
public class SpringFoxConfig {
    //TODO 구현예정
}
