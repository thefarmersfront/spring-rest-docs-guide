package com.kurly.tet.guide.springrestdocs.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

public class JsonUtils {

    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        javaTimeModule.addSerializer(ZonedDateTime.class,
                new ZonedDateTimeSerializer(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        javaTimeModule.addDeserializer(ZonedDateTime.class, InstantDeserializer.ZONED_DATE_TIME);

        MAPPER.registerModule(javaTimeModule);
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }


    private JsonUtils() {
        throw new UnsupportedOperationException();
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }

    public static <T> T fromJson(InputStream inputStream, Class<T> clazz) {
        if (isNull(inputStream)) {
            return null;
        }

        try {
            return MAPPER.readValue(inputStream, clazz);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        if (isNull(json)) {
            return null;
        }

        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static <T> T fromJson(JsonNode jsonNode, Class<T> clazz) {
        if (isNull(jsonNode)) {
            return null;
        }

        try {
            return MAPPER.readValue(jsonNode.traverse(), clazz);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static <T> T fromJson(InputStream inputStream, TypeReference<T> typeReference) {
        if (isNull(inputStream)) {
            return null;
        }

        try {
            return MAPPER.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        if (isNull(json)) {
            return null;
        }

        try {
            return MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static <T> T fromJson(JsonNode jsonNode, TypeReference<T> typeReference) {
        if (isNull(jsonNode)) {
            return null;
        }

        try {
            return MAPPER.readValue(jsonNode.traverse(), typeReference);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static <T> T fromJson(byte[] bytes, TypeReference<T> typeReference) {
        if (isNull(bytes) || bytes.length == 0) {
            return null;
        }

        try {
            return MAPPER.readValue(bytes, typeReference);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static <T> T fromJson(byte[] bytes, Class<T> clazz) {
        if (isNull(bytes) || bytes.length == 0) {
            return null;
        }

        try {
            return MAPPER.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static JsonNode fromJson(InputStream inputStream) {
        if (isNull(inputStream)) {
            return null;
        }

        try {
            return MAPPER.readTree(inputStream);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static JsonNode fromJson(String json) {
        if (isNull(json)) {
            return null;
        }

        try {
            return MAPPER.readTree(json);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static <T> List<T> fromJsonArray(InputStream inputStream, Class<T> clazz) {
        if (isNull(inputStream)) {
            return Collections.emptyList();
        }

        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, clazz);

        try {
            return MAPPER.readValue(inputStream, collectionType);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
        if (isNull(json)) {
            return Collections.emptyList();
        }

        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, clazz);

        try {
            return MAPPER.readValue(json, collectionType);
        } catch (IOException e) {
            throw new JsonDecodeException(e);
        }
    }

    public static String toJson(final Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            throw new JsonEncodeException(e);
        }
    }

    public static byte[] toJsonByte(final Object object) {
        try {
            return MAPPER.writeValueAsBytes(object);
        } catch (IOException e) {
            throw new JsonEncodeException(e);
        }
    }

    public static String toPrettyJson(final Object object) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (IOException e) {
            throw new JsonEncodeException(e);
        }
    }

    public static String get(JsonNode jsonNode) {
        if (jsonNode == null || jsonNode.isNull()) {
            return null;
        }

        return jsonNode.asText();
    }

    public static <T> T convertValue(Object obj, TypeReference<T> clazz) {
        return MAPPER.convertValue(obj, clazz);
    }

    public static class JsonEncodeException extends RuntimeException {

        private static final long serialVersionUID = 4975703115049362769L;

        public JsonEncodeException(Throwable cause) {
            super(cause);
        }
    }

    public static class JsonDecodeException extends RuntimeException {

        private static final long serialVersionUID = -2651564042039413190L;

        public JsonDecodeException(Throwable cause) {
            super(cause);
        }
    }
}
