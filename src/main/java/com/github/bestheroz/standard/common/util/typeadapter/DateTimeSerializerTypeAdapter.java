package com.github.bestheroz.standard.common.util.typeadapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.joda.time.DateTime;

import java.lang.reflect.Type;

public class DateTimeSerializerTypeAdapter implements JsonSerializer<DateTime> {
    @Override
    public JsonElement serialize(final DateTime date, final Type typeOfSrc, final JsonSerializationContext context) {
        return new JsonPrimitive(date.toDate().getTime());
    }
}
