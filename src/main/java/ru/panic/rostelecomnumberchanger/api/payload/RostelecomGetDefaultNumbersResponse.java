package ru.panic.rostelecomnumberchanger.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RostelecomGetDefaultNumbersResponse {
    private List<Number> numbers;

    @Getter
    @Setter
    public static class Number {
        private String id;

        @JsonProperty("number")
        private String phoneNumber;

        private int cost;
    }
}
