package ru.panic.rostelecomnumberchanger.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
    @JsonProperty("account_id")
    private long accountId;

    @JsonProperty("cookie_string")
    private String cookieString;
}
