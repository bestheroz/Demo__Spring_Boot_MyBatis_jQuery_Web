package com.github.bestheroz.standard.common.util.typeadapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Type;

public class JsonSerializerTypeAdapter implements JsonSerializer<Json> {
    @Override
    public JsonElement serialize(final Json json, final Type type, final JsonSerializationContext context) {
        return JsonParser.parseString(json.value());
    }
}
