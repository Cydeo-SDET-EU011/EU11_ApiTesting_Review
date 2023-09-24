package week3.POJO;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.*;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllSpartans {

    //@JsonProperty("")
    private List<SingleSpartan> spartans;
}
