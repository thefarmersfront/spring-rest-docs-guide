package com.kurly.tet.guide.springrestdocs.infrastructure.web.common.component;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.kurly.tet.guide.springrestdocs.common.util.LocalDateTimeUtils;
import com.kurly.tet.guide.springrestdocs.common.util.LocalDateUtils;
import com.kurly.tet.guide.springrestdocs.common.util.LocalTimeUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;

@JsonComponent
public class LocalDateTimeJsonComponent {
    public static class Serializer extends JsonSerializer<LocalDateTime> {

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(LocalDateTimeUtils.toString(value));
        }
    }

    public static class Deserializer extends JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return LocalDateTimeUtils.toLocalDateTime(p.getText());
        }
    }
}
