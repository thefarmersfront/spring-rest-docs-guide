package com.kurly.tet.guide.springrestdocs.documenation;

import com.kurly.tet.guide.springrestdocs.common.util.JsonUtils;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.advisor.ApiResponseWrappingAdvisor;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.handler.GlobalRestControllerExceptionHandler;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MockMvcFactory {
    public static MockMvc getMockMvc(Object... controllers) {
        return getMockMvcBuilder(controllers).build();
    }

    public static MockMvc getRestDocsMockMvc(RestDocumentationContextProvider restDocumentationContextProvider, String host, Object... controllers) {
        var documentationConfigurer = MockMvcRestDocumentation.documentationConfiguration(restDocumentationContextProvider);
        documentationConfigurer.uris().withScheme("https").withHost(host).withPort(443);
        
        return getMockMvcBuilder(controllers).apply(documentationConfigurer).build();
    }

    private static StandaloneMockMvcBuilder getMockMvcBuilder(Object... controllers) {
        var conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(new LocalDateTimeConverter());
        conversionService.addConverter(new LocalDateConverter());
        conversionService.addConverter(new LocalTimeConverter());

        return MockMvcBuilders.standaloneSetup(controllers)
                .setControllerAdvice(
                        new GlobalRestControllerExceptionHandler(),
                        new ApiResponseWrappingAdvisor())
                .setConversionService(conversionService)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(JsonUtils.getMapper()));
    }

    public static class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(String source) {
            return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        }
    }

    public static class LocalDateConverter implements Converter<String, LocalDate> {

        @Override
        public LocalDate convert(String source) {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    public static class LocalTimeConverter implements Converter<String, LocalTime> {
        @Override
        public LocalTime convert(String source) {
            return LocalTime.parse(source, DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
    }
}
