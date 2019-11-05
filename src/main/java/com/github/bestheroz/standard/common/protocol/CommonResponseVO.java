package com.github.bestheroz.standard.common.protocol;

import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class CommonResponseVO implements Serializable {
    private transient static final long serialVersionUID = 646012808993720354L;
    @ApiModelProperty(value = "responseCode", position = 1, example = "S000", required = true)
    private String responseCode;
    @ApiModelProperty(value = "responseMessage", position = 2, example = "성공", required = true)
    private String responseMessage;
    @ApiModelProperty(value = "responseData", position = 3, dataType = "springfox.documentation.spring.web.json.Json")
    private JsonElement responseData;
    @ApiModelProperty(value = "additionalMessage", position = 4, example = "(Nullable)")
    private String additionalMessage;

    public JsonObject toJsonObject() {
        return MyMapperUtils.writeObjectAsJsonObject(this);
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public void setResponseMessage(final String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getAdditionalMessage() {
        return this.additionalMessage;
    }

    public void setAdditionalMessage(final String additionalMessage) {
        this.additionalMessage = additionalMessage;
    }

    public JsonElement getResponseData() {
        return this.responseData;
    }

    public void setResponseData(final Object responseData) {
        this.responseData = MyMapperUtils.writeObjectAsJsonElement(responseData);
    }

    public <T> T getResponseData(final Class<T> valueType) {
        return MyMapperUtils.writeObjectAsObject(this.responseData, valueType);
    }

    @ApiModelProperty(hidden = true)
    public boolean isSuccess() {
        return StringUtils.startsWith(this.responseCode, "S");
    }

    @ApiModelProperty(hidden = true)
    public boolean isFail() {
        return StringUtils.startsWith(this.responseCode, "F");
    }
}
