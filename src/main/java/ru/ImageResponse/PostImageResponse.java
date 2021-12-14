
package ru.ImageResponse;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Generated;
import javax.swing.*;


import io.qameta.allure.internal.shadowed.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "success",
    "data"
})
@Generated("jsonschema2pojo")
public class PostImageResponse implements Serializable
{

    @JsonProperty("status")
    public Integer status;
    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("data")
    public Data data;

}
