package ru.panic.rostelecomnumberchanger.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCookieStringRequest {
    private long id;

    @JsonProperty("cookie_string")
    private String cookieString;
}
