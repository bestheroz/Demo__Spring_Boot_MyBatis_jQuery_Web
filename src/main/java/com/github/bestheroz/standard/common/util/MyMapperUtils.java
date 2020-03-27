package com.github.bestheroz.standard.common.util;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.exception.CommonExceptionCode;
import com.github.bestheroz.standard.common.util.typeadapter.*;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.lang.reflect.Type;
import java.util.*;

@Slf4j
@UtilityClass
public class MyMapperUtils {
    private static final Gson GSON_INSTANCE = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializerTypeAdapter()).registerTypeAdapter(Date.class, new DateSerializerTypeAdapter())
            .registerTypeAdapter(DateTime.class, new DateTimeDeserializerTypeAdapter()).registerTypeAdapter(DateTime.class, new DateTimeSerializerTypeAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializerTypeAdapter()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializerTypeAdapter())
            .registerTypeAdapter(Map.class, new MapDeserializerTypeAdapter()).registerTypeAdapter(HashMap.class, new MapDeserializerTypeAdapter())
            .registerTypeAdapter(LinkedTreeMap.class, new MapDeserializerTypeAdapter()).disableHtmlEscaping().create();


    public static <T> T writeObjectAsObject(final Object content, final Class<T> returnType) throws CommonException {
        return GSON_INSTANCE.fromJson(writeObjectAsJsonElement(content), returnType);
    }

    public static <T> T writeObjectAsObject(final Object content, final Type typeOfT) throws CommonException {
        return GSON_INSTANCE.fromJson(writeObjectAsJsonElement(content), typeOfT);
    }

    public static JsonArray writeObjectAsJsonArray(final Object content) throws CommonException {
        return getCollectionTypeCatchException(content, JsonArray.class);
    }

    public static JsonObject writeObjectAsJsonObject(final Object content) throws CommonException {
        return getCollectionTypeCatchException(content, JsonObject.class);
    }

    public static Map<String, Object> writeObjectAsHashMap(final Object content) throws CommonException {
        return writeObjectAsObject(getCollectionTypeCatchException(content, JsonObject.class), new TypeToken<HashMap<String, Object>>() {
        }.getType());
    }

    public static JsonPrimitive writeObjectAsJsonPrimitive(final Object content) throws CommonException {
        return writeObjectAsJsonElement(content).getAsJsonPrimitive();
    }

    public static JsonElement writeObjectAsJsonElement(final Object content) throws CommonException {
        if (content instanceof String) {
            try {
                return JsonParser.parseString((String) content);
            } catch (final Throwable e) {
                // ignored
                return GSON_INSTANCE.toJsonTree(content);
            }
        } else {
            return GSON_INSTANCE.toJsonTree(content);
        }
    }

    public static String writeObjectAsString(final Object content) throws CommonException {
        return GSON_INSTANCE.toJson(content);
    }

    public static <T> List<T> writeObjectAsArrayList(final Object content, final Class<T> returnType) throws CommonException {
        final JsonArray array = MyMapperUtils.writeObjectAsObject(content, JsonArray.class);
        final List<T> lst = new ArrayList<>();
        for (final JsonElement json : array) {
            lst.add(MyMapperUtils.writeObjectAsObject(json, returnType));
        }
        return lst;
        // return GSON_INSTANCE.fromJson(getJsonElementWithExceptionString(content), new TypeToken<ArrayList<T>>() {
        // }.getType());
    }

    public static Gson getGsonObject() {
        return GSON_INSTANCE;
    }

    @SuppressWarnings("unchecked")
    private static <T> T getCollectionTypeCatchException(final Object content, final Class<T> returnType) {
        final JsonElement jsonElement = writeObjectAsJsonElement(content);
        if (jsonElement.isJsonPrimitive()) {
            log.warn(CommonExceptionCode.FAIL_TRANSFORM_DATA.toString());
            throw new CommonException(CommonExceptionCode.FAIL_TRANSFORM_DATA, jsonElement);
        } else if (jsonElement.isJsonNull()) {
            if (returnType == JsonObject.class) {
                return (T) new JsonObject();
            } else if (returnType == JsonArray.class) {
                return (T) new JsonArray();
            } else if (returnType == Map.class) {
                return (T) new HashMap<String, Object>();
            }
        }

        return writeObjectAsObject(jsonElement, returnType);
    }
}
