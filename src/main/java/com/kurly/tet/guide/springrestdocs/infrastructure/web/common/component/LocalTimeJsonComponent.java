package com.kurly.tet.guide.springrestdocs.infrastructure.web.common.component;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.kurly.tet.guide.springrestdocs.common.util.LocalDateTimeUtils;
import com.kurly.tet.guide.springrestdocs.common.util.LocalTimeUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalTime;

@JsonComponent
public class LocalTimeJsonComponent {
    public static class Serializer extends JsonSerializer<LocalTime> {
        @Override
        public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(LocalTimeUtils.toString(value));
        }
    }

    public static class Deserializer extends JsonDeserializer<LocalTime> {

        @Override
        public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return LocalTimeUtils.toLocalTime(p.getText());
        }
    }
}
