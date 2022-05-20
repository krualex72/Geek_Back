package homework4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder( {
        "item",
        "aisle",
        "parse"
})
@Data
public class AddShopListRequest {
    @JsonProperty("item")
    private String item;
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("parse")
    private Boolean parse;

    public AddShopListRequest(String item, String aisle, Boolean parse) {
        this.item = item;
        this.aisle = aisle;
        this.parse = parse;
    }
}

