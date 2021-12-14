
package ru.ImageResponse;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;




@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "deletehash",
    "account_id",
    "account_url",
    "ad_type",
    "ad_url",
    "title",
    "description",
    "name",
    "type",
    "width",
    "height",
    "size",
    "views",
    "section",
    "vote",
    "bandwidth",
    "animated",
    "favorite",
    "in_gallery",
    "in_most_viral",
    "has_sound",
    "is_ad",
    "nsfw",
    "link",
    "tags",
    "datetime",
    "mp4",
    "hls"
})
@Generated("jsonschema2pojo")
public class Data implements Serializable
{

    @JsonProperty("id")
    public String id;
    @JsonProperty("deletehash")
    public String deletehash;
    @JsonProperty("account_id")
    public Object accountId;
    @JsonProperty("account_url")
    public Object accountUrl;
    @JsonProperty("ad_type")
    public Object adType;
    @JsonProperty("ad_url")
    public Object adUrl;
    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public String description;
    @JsonProperty("name")
    public String name;
    @JsonProperty("type")
    public String type;
    @JsonProperty("width")
    public Integer width;
    @JsonProperty("height")
    public Integer height;
    @JsonProperty("size")
    public Integer size;
    @JsonProperty("views")
    public Integer views;
    @JsonProperty("section")
    public Object section;
    @JsonProperty("vote")
    public Object vote;
    @JsonProperty("bandwidth")
    public Integer bandwidth;
    @JsonProperty("animated")
    public Boolean animated;
    @JsonProperty("favorite")
    public Boolean favorite;
    @JsonProperty("in_gallery")
    public Boolean inGallery;
    @JsonProperty("in_most_viral")
    public Boolean inMostViral;
    @JsonProperty("has_sound")
    public Boolean hasSound;
    @JsonProperty("is_ad")
    public Boolean isAd;
    @JsonProperty("nsfw")
    public Object nsfw;
    @JsonProperty("link")
    public String link;
    @JsonProperty("tags")
    public List<Object> tags = null;
    @JsonProperty("datetime")
    public Integer datetime;
    @JsonProperty("mp4")
    public String mp4;
    @JsonProperty("hls")
    public String hls;
    @JsonProperty("edited")
    public String edited;
    @JsonProperty("ad_config")
    public Object ad_config;
    @JsonProperty("gifv")
    public String gifv;
    @JsonProperty("mp4_size")
    public String mp4_size;
    @JsonProperty("looping")
    public String looping;
}
