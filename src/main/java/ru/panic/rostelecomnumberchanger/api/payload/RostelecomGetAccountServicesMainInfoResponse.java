package ru.panic.rostelecomnumberchanger.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RostelecomGetAccountServicesMainInfoResponse {
    @JsonProperty("services")
    private Map<String, AccountMainInfo> service;

    @Getter
    @Setter
    public static class AccountMainInfo {
        @JsonProperty("number")
        private String phoneNumber;
    }
}
