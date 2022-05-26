
package course.java.model.jsonschemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "authors",
    "publisher",
    "publishedDate",
    "description",
    "industryIdentifiers",
    "readingModes",
    "pageCount",
    "printType",
    "categories",
    "maturityRating",
    "allowAnonLogging",
    "contentVersion",
    "panelizationSummary",
    "imageLinks",
    "language",
    "previewLink",
    "infoLink",
    "canonicalVolumeLink",
    "subtitle",
    "averageRating",
    "ratingsCount"
})
public class VolumeInfo {

    @JsonProperty("title")
    private String title;
    @JsonProperty("authors")
    private List<String> authors = new ArrayList<String>();
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("publishedDate")
    private String publishedDate;
    @JsonProperty("description")
    private String description;
    @JsonProperty("industryIdentifiers")
    private List<IndustryIdentifier> industryIdentifiers = new ArrayList<IndustryIdentifier>();
    @JsonProperty("readingModes")
    private ReadingModes readingModes;
    @JsonProperty("pageCount")
    private Integer pageCount;
    @JsonProperty("printType")
    private String printType;
    @JsonProperty("categories")
    private List<String> categories = new ArrayList<String>();
    @JsonProperty("maturityRating")
    private String maturityRating;
    @JsonProperty("allowAnonLogging")
    private Boolean allowAnonLogging;
    @JsonProperty("contentVersion")
    private String contentVersion;
    @JsonProperty("panelizationSummary")
    private PanelizationSummary panelizationSummary;
    @JsonProperty("imageLinks")
    private ImageLinks imageLinks;
    @JsonProperty("language")
    private String language;
    @JsonProperty("previewLink")
    private String previewLink;
    @JsonProperty("infoLink")
    private String infoLink;
    @JsonProperty("canonicalVolumeLink")
    private String canonicalVolumeLink;
    @JsonProperty("subtitle")
    private String subtitle;
    @JsonProperty("averageRating")
    private Integer averageRating;
    @JsonProperty("ratingsCount")
    private Integer ratingsCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("authors")
    public List<String> getAuthors() {
        return authors;
    }

    @JsonProperty("authors")
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("publishedDate")
    public String getPublishedDate() {
        return publishedDate;
    }

    @JsonProperty("publishedDate")
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("industryIdentifiers")
    public List<IndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    @JsonProperty("industryIdentifiers")
    public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    @JsonProperty("readingModes")
    public ReadingModes getReadingModes() {
        return readingModes;
    }

    @JsonProperty("readingModes")
    public void setReadingModes(ReadingModes readingModes) {
        this.readingModes = readingModes;
    }

    @JsonProperty("pageCount")
    public Integer getPageCount() {
        return pageCount;
    }

    @JsonProperty("pageCount")
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @JsonProperty("printType")
    public String getPrintType() {
        return printType;
    }

    @JsonProperty("printType")
    public void setPrintType(String printType) {
        this.printType = printType;
    }

    @JsonProperty("categories")
    public List<String> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @JsonProperty("maturityRating")
    public String getMaturityRating() {
        return maturityRating;
    }

    @JsonProperty("maturityRating")
    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    @JsonProperty("allowAnonLogging")
    public Boolean getAllowAnonLogging() {
        return allowAnonLogging;
    }

    @JsonProperty("allowAnonLogging")
    public void setAllowAnonLogging(Boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    @JsonProperty("contentVersion")
    public String getContentVersion() {
        return contentVersion;
    }

    @JsonProperty("contentVersion")
    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    @JsonProperty("panelizationSummary")
    public PanelizationSummary getPanelizationSummary() {
        return panelizationSummary;
    }

    @JsonProperty("panelizationSummary")
    public void setPanelizationSummary(PanelizationSummary panelizationSummary) {
        this.panelizationSummary = panelizationSummary;
    }

    @JsonProperty("imageLinks")
    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    @JsonProperty("imageLinks")
    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("previewLink")
    public String getPreviewLink() {
        return previewLink;
    }

    @JsonProperty("previewLink")
    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    @JsonProperty("infoLink")
    public String getInfoLink() {
        return infoLink;
    }

    @JsonProperty("infoLink")
    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    @JsonProperty("canonicalVolumeLink")
    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    @JsonProperty("canonicalVolumeLink")
    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    @JsonProperty("subtitle")
    public String getSubtitle() {
        return subtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @JsonProperty("averageRating")
    public Integer getAverageRating() {
        return averageRating;
    }

    @JsonProperty("averageRating")
    public void setAverageRating(Integer averageRating) {
        this.averageRating = averageRating;
    }

    @JsonProperty("ratingsCount")
    public Integer getRatingsCount() {
        return ratingsCount;
    }

    @JsonProperty("ratingsCount")
    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
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
        sb.append(VolumeInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("authors");
        sb.append('=');
        sb.append(((this.authors == null)?"<null>":this.authors));
        sb.append(',');
        sb.append("publisher");
        sb.append('=');
        sb.append(((this.publisher == null)?"<null>":this.publisher));
        sb.append(',');
        sb.append("publishedDate");
        sb.append('=');
        sb.append(((this.publishedDate == null)?"<null>":this.publishedDate));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("industryIdentifiers");
        sb.append('=');
        sb.append(((this.industryIdentifiers == null)?"<null>":this.industryIdentifiers));
        sb.append(',');
        sb.append("readingModes");
        sb.append('=');
        sb.append(((this.readingModes == null)?"<null>":this.readingModes));
        sb.append(',');
        sb.append("pageCount");
        sb.append('=');
        sb.append(((this.pageCount == null)?"<null>":this.pageCount));
        sb.append(',');
        sb.append("printType");
        sb.append('=');
        sb.append(((this.printType == null)?"<null>":this.printType));
        sb.append(',');
        sb.append("categories");
        sb.append('=');
        sb.append(((this.categories == null)?"<null>":this.categories));
        sb.append(',');
        sb.append("maturityRating");
        sb.append('=');
        sb.append(((this.maturityRating == null)?"<null>":this.maturityRating));
        sb.append(',');
        sb.append("allowAnonLogging");
        sb.append('=');
        sb.append(((this.allowAnonLogging == null)?"<null>":this.allowAnonLogging));
        sb.append(',');
        sb.append("contentVersion");
        sb.append('=');
        sb.append(((this.contentVersion == null)?"<null>":this.contentVersion));
        sb.append(',');
        sb.append("panelizationSummary");
        sb.append('=');
        sb.append(((this.panelizationSummary == null)?"<null>":this.panelizationSummary));
        sb.append(',');
        sb.append("imageLinks");
        sb.append('=');
        sb.append(((this.imageLinks == null)?"<null>":this.imageLinks));
        sb.append(',');
        sb.append("language");
        sb.append('=');
        sb.append(((this.language == null)?"<null>":this.language));
        sb.append(',');
        sb.append("previewLink");
        sb.append('=');
        sb.append(((this.previewLink == null)?"<null>":this.previewLink));
        sb.append(',');
        sb.append("infoLink");
        sb.append('=');
        sb.append(((this.infoLink == null)?"<null>":this.infoLink));
        sb.append(',');
        sb.append("canonicalVolumeLink");
        sb.append('=');
        sb.append(((this.canonicalVolumeLink == null)?"<null>":this.canonicalVolumeLink));
        sb.append(',');
        sb.append("subtitle");
        sb.append('=');
        sb.append(((this.subtitle == null)?"<null>":this.subtitle));
        sb.append(',');
        sb.append("averageRating");
        sb.append('=');
        sb.append(((this.averageRating == null)?"<null>":this.averageRating));
        sb.append(',');
        sb.append("ratingsCount");
        sb.append('=');
        sb.append(((this.ratingsCount == null)?"<null>":this.ratingsCount));
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
        result = ((result* 31)+((this.industryIdentifiers == null)? 0 :this.industryIdentifiers.hashCode()));
        result = ((result* 31)+((this.pageCount == null)? 0 :this.pageCount.hashCode()));
        result = ((result* 31)+((this.printType == null)? 0 :this.printType.hashCode()));
        result = ((result* 31)+((this.readingModes == null)? 0 :this.readingModes.hashCode()));
        result = ((result* 31)+((this.previewLink == null)? 0 :this.previewLink.hashCode()));
        result = ((result* 31)+((this.canonicalVolumeLink == null)? 0 :this.canonicalVolumeLink.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.language == null)? 0 :this.language.hashCode()));
        result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
        result = ((result* 31)+((this.imageLinks == null)? 0 :this.imageLinks.hashCode()));
        result = ((result* 31)+((this.panelizationSummary == null)? 0 :this.panelizationSummary.hashCode()));
        result = ((result* 31)+((this.subtitle == null)? 0 :this.subtitle.hashCode()));
        result = ((result* 31)+((this.averageRating == null)? 0 :this.averageRating.hashCode()));
        result = ((result* 31)+((this.publisher == null)? 0 :this.publisher.hashCode()));
        result = ((result* 31)+((this.ratingsCount == null)? 0 :this.ratingsCount.hashCode()));
        result = ((result* 31)+((this.publishedDate == null)? 0 :this.publishedDate.hashCode()));
        result = ((result* 31)+((this.categories == null)? 0 :this.categories.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.maturityRating == null)? 0 :this.maturityRating.hashCode()));
        result = ((result* 31)+((this.allowAnonLogging == null)? 0 :this.allowAnonLogging.hashCode()));
        result = ((result* 31)+((this.contentVersion == null)? 0 :this.contentVersion.hashCode()));
        result = ((result* 31)+((this.authors == null)? 0 :this.authors.hashCode()));
        result = ((result* 31)+((this.infoLink == null)? 0 :this.infoLink.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VolumeInfo) == false) {
            return false;
        }
        VolumeInfo rhs = ((VolumeInfo) other);
        return ((((((((((((((((((((((((this.industryIdentifiers == rhs.industryIdentifiers)||((this.industryIdentifiers!= null)&&this.industryIdentifiers.equals(rhs.industryIdentifiers)))&&((this.pageCount == rhs.pageCount)||((this.pageCount!= null)&&this.pageCount.equals(rhs.pageCount))))&&((this.printType == rhs.printType)||((this.printType!= null)&&this.printType.equals(rhs.printType))))&&((this.readingModes == rhs.readingModes)||((this.readingModes!= null)&&this.readingModes.equals(rhs.readingModes))))&&((this.previewLink == rhs.previewLink)||((this.previewLink!= null)&&this.previewLink.equals(rhs.previewLink))))&&((this.canonicalVolumeLink == rhs.canonicalVolumeLink)||((this.canonicalVolumeLink!= null)&&this.canonicalVolumeLink.equals(rhs.canonicalVolumeLink))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.imageLinks == rhs.imageLinks)||((this.imageLinks!= null)&&this.imageLinks.equals(rhs.imageLinks))))&&((this.panelizationSummary == rhs.panelizationSummary)||((this.panelizationSummary!= null)&&this.panelizationSummary.equals(rhs.panelizationSummary))))&&((this.subtitle == rhs.subtitle)||((this.subtitle!= null)&&this.subtitle.equals(rhs.subtitle))))&&((this.averageRating == rhs.averageRating)||((this.averageRating!= null)&&this.averageRating.equals(rhs.averageRating))))&&((this.publisher == rhs.publisher)||((this.publisher!= null)&&this.publisher.equals(rhs.publisher))))&&((this.ratingsCount == rhs.ratingsCount)||((this.ratingsCount!= null)&&this.ratingsCount.equals(rhs.ratingsCount))))&&((this.publishedDate == rhs.publishedDate)||((this.publishedDate!= null)&&this.publishedDate.equals(rhs.publishedDate))))&&((this.categories == rhs.categories)||((this.categories!= null)&&this.categories.equals(rhs.categories))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.maturityRating == rhs.maturityRating)||((this.maturityRating!= null)&&this.maturityRating.equals(rhs.maturityRating))))&&((this.allowAnonLogging == rhs.allowAnonLogging)||((this.allowAnonLogging!= null)&&this.allowAnonLogging.equals(rhs.allowAnonLogging))))&&((this.contentVersion == rhs.contentVersion)||((this.contentVersion!= null)&&this.contentVersion.equals(rhs.contentVersion))))&&((this.authors == rhs.authors)||((this.authors!= null)&&this.authors.equals(rhs.authors))))&&((this.infoLink == rhs.infoLink)||((this.infoLink!= null)&&this.infoLink.equals(rhs.infoLink))));
    }

}
