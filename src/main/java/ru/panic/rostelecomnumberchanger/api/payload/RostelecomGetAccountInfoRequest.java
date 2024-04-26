package ru.panic.rostelecomnumberchanger.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RostelecomGetAccountInfoRequest {
    private long accountId;

    @JsonProperty("client_uuid")
    private String clientUuid;

    @JsonProperty("current_page")
    private String currentPage;

    @JsonProperty("page_uuid")
    private String pageUuid;
}
