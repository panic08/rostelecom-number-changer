package ru.panic.rostelecomnumberchanger.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetJsonCookieStringResponse {
    @JsonProperty("json_cookie_string")
    private String jsonCookieString;
}
