
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
    "kind",
    "id",
    "etag",
    "selfLink",
    "volumeInfo",
    "saleInfo",
    "accessInfo"
})
public class BookInfo {

    @JsonProperty("kind")
    private String kind;
    @JsonProperty("id")
    private String id;
    @JsonProperty("etag")
    private String etag;
    @JsonProperty("selfLink")
    private String selfLink;
    @JsonProperty("volumeInfo")
    private VolumeInfo volumeInfo;
    @JsonProperty("saleInfo")
    private SaleInfo saleInfo;
    @JsonProperty("accessInfo")
    private AccessInfo accessInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private boolean hasVolumeInfo;

    /**
     * 
     * @return
     *     The kind
     */
    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    /**
     * 
     * @param kind
     *     The kind
     */
    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The etag
     */
    @JsonProperty("etag")
    public String getEtag() {
        return etag;
    }

    /**
     * 
     * @param etag
     *     The etag
     */
    @JsonProperty("etag")
    public void setEtag(String etag) {
        this.etag = etag;
    }

    /**
     * 
     * @return
     *     The selfLink
     */
    @JsonProperty("selfLink")
    public String getSelfLink() {
        return selfLink;
    }

    /**
     * 
     * @param selfLink
     *     The selfLink
     */
    @JsonProperty("selfLink")
    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    /**
     * 
     * @return
     *     The volumeInfo
     */
    @JsonProperty("volumeInfo")
    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    /**
     * 
     * @param volumeInfo
     *     The volumeInfo
     */
    @JsonProperty("volumeInfo")
    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
        this.hasVolumeInfo = true;
    }

    /**
     * 
     * @return
     *     The saleInfo
     */
    @JsonProperty("saleInfo")
    public SaleInfo getSaleInfo() {
        return saleInfo;
    }

    /**
     * 
     * @param saleInfo
     *     The saleInfo
     */
    @JsonProperty("saleInfo")
    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }

    /**
     * 
     * @return
     *     The accessInfo
     */
    @JsonProperty("accessInfo")
    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    /**
     * 
     * @param accessInfo
     *     The accessInfo
     */
    @JsonProperty("accessInfo")
    public void setAccessInfo(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public boolean hasVolumeInfo() {
        return this.hasVolumeInfo;
    }
}
