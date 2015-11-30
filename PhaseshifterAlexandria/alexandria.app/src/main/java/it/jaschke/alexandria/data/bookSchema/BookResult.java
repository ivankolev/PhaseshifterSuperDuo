package it.jaschke.alexandria.data.bookSchema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "kind",
        "totalItems",
        "items"
})
public class BookResult {
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("totalItems")
    private Integer totalItems;
    @JsonProperty("items")
    private List<BookInfo> items;

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("totalItems")
    public Integer getTotalItems() {
        return totalItems;
    }

    @JsonProperty("totalItems")
    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    @JsonProperty("items")
    public List<BookInfo> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<BookInfo> items) {
        this.items = items;
    }
}
