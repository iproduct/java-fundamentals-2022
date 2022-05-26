
package course.java.model.jsonschemas;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "kind",
    "id",
    "etag",
    "selfLink",
    "volumeInfo",
    "saleInfo",
    "accessInfo",
    "searchInfo"
})
public class Item {

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
    @JsonProperty("searchInfo")
    private SearchInfo searchInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("etag")
    public String getEtag() {
        return etag;
    }

    @JsonProperty("etag")
    public void setEtag(String etag) {
        this.etag = etag;
    }

    @JsonProperty("selfLink")
    public String getSelfLink() {
        return selfLink;
    }

    @JsonProperty("selfLink")
    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    @JsonProperty("volumeInfo")
    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    @JsonProperty("volumeInfo")
    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    @JsonProperty("saleInfo")
    public SaleInfo getSaleInfo() {
        return saleInfo;
    }

    @JsonProperty("saleInfo")
    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }

    @JsonProperty("accessInfo")
    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    @JsonProperty("accessInfo")
    public void setAccessInfo(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }

    @JsonProperty("searchInfo")
    public SearchInfo getSearchInfo() {
        return searchInfo;
    }

    @JsonProperty("searchInfo")
    public void setSearchInfo(SearchInfo searchInfo) {
        this.searchInfo = searchInfo;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Item.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("kind");
        sb.append('=');
        sb.append(((this.kind == null)?"<null>":this.kind));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("etag");
        sb.append('=');
        sb.append(((this.etag == null)?"<null>":this.etag));
        sb.append(',');
        sb.append("selfLink");
        sb.append('=');
        sb.append(((this.selfLink == null)?"<null>":this.selfLink));
        sb.append(',');
        sb.append("volumeInfo");
        sb.append('=');
        sb.append(((this.volumeInfo == null)?"<null>":this.volumeInfo));
        sb.append(',');
        sb.append("saleInfo");
        sb.append('=');
        sb.append(((this.saleInfo == null)?"<null>":this.saleInfo));
        sb.append(',');
        sb.append("accessInfo");
        sb.append('=');
        sb.append(((this.accessInfo == null)?"<null>":this.accessInfo));
        sb.append(',');
        sb.append("searchInfo");
        sb.append('=');
        sb.append(((this.searchInfo == null)?"<null>":this.searchInfo));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.saleInfo == null)? 0 :this.saleInfo.hashCode()));
        result = ((result* 31)+((this.searchInfo == null)? 0 :this.searchInfo.hashCode()));
        result = ((result* 31)+((this.kind == null)? 0 :this.kind.hashCode()));
        result = ((result* 31)+((this.volumeInfo == null)? 0 :this.volumeInfo.hashCode()));
        result = ((result* 31)+((this.etag == null)? 0 :this.etag.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.accessInfo == null)? 0 :this.accessInfo.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.selfLink == null)? 0 :this.selfLink.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Item) == false) {
            return false;
        }
        Item rhs = ((Item) other);
        return ((((((((((this.saleInfo == rhs.saleInfo)||((this.saleInfo!= null)&&this.saleInfo.equals(rhs.saleInfo)))&&((this.searchInfo == rhs.searchInfo)||((this.searchInfo!= null)&&this.searchInfo.equals(rhs.searchInfo))))&&((this.kind == rhs.kind)||((this.kind!= null)&&this.kind.equals(rhs.kind))))&&((this.volumeInfo == rhs.volumeInfo)||((this.volumeInfo!= null)&&this.volumeInfo.equals(rhs.volumeInfo))))&&((this.etag == rhs.etag)||((this.etag!= null)&&this.etag.equals(rhs.etag))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.accessInfo == rhs.accessInfo)||((this.accessInfo!= null)&&this.accessInfo.equals(rhs.accessInfo))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.selfLink == rhs.selfLink)||((this.selfLink!= null)&&this.selfLink.equals(rhs.selfLink))));
    }

}
