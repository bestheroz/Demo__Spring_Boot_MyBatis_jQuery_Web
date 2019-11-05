package com.github.bestheroz.standard.common.util.typeadapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

public class DateSerializerTypeAdapter implements JsonSerializer<Date> {
    @Override
    public JsonElement serialize(final Date date, final Type typeOfSrc, final JsonSerializationContext context) {
        return new JsonPrimitive(date.getTime());
    }
}
