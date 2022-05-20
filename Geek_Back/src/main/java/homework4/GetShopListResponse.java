package homework4;

        import java.util.ArrayList;
        import java.util.List;
        import com.fasterxml.jackson.annotation.JsonInclude;
        import com.fasterxml.jackson.annotation.JsonProperty;
        import com.fasterxml.jackson.annotation.JsonPropertyOrder;
        import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisles",
        "cost",
        "startDate",
        "endDate"
})
@Data
public class GetShopListResponse {

    @JsonProperty("aisles")
    private List<Aisle> aisles = new ArrayList<Aisle>();
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("startDate")
    private Integer startDate;
    @JsonProperty("endDate")
    private Integer endDate;

}