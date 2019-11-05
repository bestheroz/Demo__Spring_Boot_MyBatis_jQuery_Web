package com.github.bestheroz.standard.common.util.typeadapter;

import com.github.bestheroz.standard.common.util.MyDateUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import org.joda.time.LocalDateTime;

import java.lang.reflect.Type;

public class LocalDateTimeDeserializerTypeAdapter implements JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        return MyDateUtils.getLocalDateTimeIgnoreException(json.getAsString());
    }
}
