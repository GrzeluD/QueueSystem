package com.queuesystem.messageParser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static <T> T parseJson(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }
}
