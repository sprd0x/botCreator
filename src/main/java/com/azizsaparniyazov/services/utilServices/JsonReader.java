package com.azizsaparniyazov.services.utilServices;


//TODO Jamol qilishi kerak. Ushbu klassda json oqib olishga tool bolishi kerak

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.util.List;

@UtilityClass
public class JsonReader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> readGson(String path, TypeReference<List<T>> typeReference) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectMapper.readValue(new File(path), typeReference);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(" ERROR!!!");
        }
    }

}
