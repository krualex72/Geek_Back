package homework4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "amount",
        "unit"
})
@Data
public class Metric {

    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("unit")
    private String unit;

}