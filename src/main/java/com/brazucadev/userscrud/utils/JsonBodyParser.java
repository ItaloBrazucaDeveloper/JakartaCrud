package com.brazucadev.userscrud.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class JsonBodyParser {
    public static JsonObject parseBodyAsJson(HttpServletRequest request) throws IOException {
        String body = new BufferedReader(request.getReader())
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        return JsonParser.parseString(body).getAsJsonObject();
    }
}