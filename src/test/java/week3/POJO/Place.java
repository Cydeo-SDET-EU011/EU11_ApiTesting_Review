package week3.POJO;

import com.fasterxml.jackson.annotation.*;
import lombok.*;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

    @JsonProperty("place name")
    private String placeName;

    private String longitude;

    private String  state;

    @JsonProperty("state abbreviation")
    private String stateAbb;

    private String latitude;
}
