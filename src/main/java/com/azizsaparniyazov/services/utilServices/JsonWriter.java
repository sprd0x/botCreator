package com.azizsaparniyazov.services.utilServices;

//TODO Jamol qilishi kerak. Ushbu klassda obyektni jsonga o'giradigan tool bolishi kerak

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class JsonWriter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> void writeGson(T t, String path) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(path), t);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR!!!");
        }
    }
}
