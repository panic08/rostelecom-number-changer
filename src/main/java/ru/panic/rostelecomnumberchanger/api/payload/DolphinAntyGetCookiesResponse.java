package ru.panic.rostelecomnumberchanger.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.panic.rostelecomnumberchanger.util.cookie.JsonCookie;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class DolphinAntyGetCookiesResponse {
    @JsonProperty("success")
    private boolean isSuccess;

    private Set<JsonCookie> data;
}
