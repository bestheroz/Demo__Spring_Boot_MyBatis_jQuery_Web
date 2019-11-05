package com.github.bestheroz.standard.common.util.typeadapter;

import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapDeserializerTypeAdapter implements JsonDeserializer<Map<String, Object>> {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Map<String, Object> deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
        final Map<String, Object> map = new LinkedHashMap<>();
        final JsonObject jsonObject = json.getAsJsonObject();
        for (final Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            final String key = entry.getKey();
            final JsonElement value = entry.getValue();
            if (value.isJsonArray()) {
                map.put(key, MyMapperUtils.getGsonObject().fromJson(value, ArrayList.class));
            } else if (value.isJsonPrimitive()) {
                // System.err.println(value.toString());
                // String 처리
                if (StringUtils.startsWith(value.toString(), "\"") && StringUtils.endsWith(value.toString(), "\"")) {
                    // System.err.println("String");
                    map.put(key, value.getAsString());
                } else // Double
                    if (StringUtils.countMatches(value.toString(), ".") == 1 || StringUtils.countMatches(value.toString(), ".") == 1 && StringUtils.countMatches(value.toString(), "E") == 1) {
                        // System.err.println("Double");
                        map.put(key, value.getAsDouble());
                    } else // Long
                        if (StringUtils.isNumeric(value.toString())) {
                            // System.err.println("Long");
                            map.put(key, value.getAsLong());
                        } else // Boolean
                            if (value.getAsJsonPrimitive().isBoolean()) {
                                // System.err.println("Boolean");
                                map.put(key, value.getAsBoolean());
                            } else // null
                                if (value.getAsJsonPrimitive().isJsonNull()) {
                                    // System.err.println("null");
                                    map.put(key, null);
                                } else { // else: String
                                    // System.err.println("else String");
                                    map.put(key, value.getAsString());
                                }
            } else if (value.isJsonObject()) {
                map.put(key, MyMapperUtils.getGsonObject().fromJson(value, HashMap.class));
            }

        }
        return map;
    }
}