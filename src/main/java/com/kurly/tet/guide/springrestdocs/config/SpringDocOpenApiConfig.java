package com.kurly.tet.guide.springrestdocs.config;

import org.springframework.context.annotation.Profile;

@Profile({Constant.PROFILE_LOCAL, Constant.PROFILE_DEV, Constant.PROFILE_STAGE})
public class SpringDocOpenApiConfig {
}
