package ru.panic.rostelecomnumberchanger.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCookieStringRequest {
    private String key;

    @JsonProperty("cookie_string")
    private String cookieString;
}
