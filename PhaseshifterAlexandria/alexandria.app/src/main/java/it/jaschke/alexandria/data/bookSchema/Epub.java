
package it.jaschke.alexandria.data.bookSchema;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "isAvailable",
    "acsTokenLink"
})
public class Epub {

    @JsonProperty("isAvailable")
    private Boolean isAvailable;
    @JsonProperty("acsTokenLink")
    private String acsTokenLink;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The isAvailable
     */
    @JsonProperty("isAvailable")
    public Boolean getIsAvailable() {
        return isAvailable;
    }

    /**
     * 
     * @param isAvailable
     *     The isAvailable
     */
    @JsonProperty("isAvailable")
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * 
     * @return
     *     The acsTokenLink
     */
    @JsonProperty("acsTokenLink")
    public String getAcsTokenLink() {
        return acsTokenLink;
    }

    /**
     * 
     * @param acsTokenLink
     *     The acsTokenLink
     */
    @JsonProperty("acsTokenLink")
    public void setAcsTokenLink(String acsTokenLink) {
        this.acsTokenLink = acsTokenLink;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
