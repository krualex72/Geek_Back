package homework4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "original",
        "metric",
        "us"
})
@Data
public class Measures {

    @JsonProperty("original")
    private Original original;
    @JsonProperty("metric")
    private Metric metric;
    @JsonProperty("us")
    private Us us;

}