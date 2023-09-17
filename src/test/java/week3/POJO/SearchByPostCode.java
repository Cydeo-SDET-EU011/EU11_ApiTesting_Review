package week3.POJO;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.*;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchByPostCode {

    @JsonProperty("post code")
    private String postCode;

    private String country;

    @JsonProperty("country abbreviation")
    private String countryAbb;

    private List<Place> places;
}
