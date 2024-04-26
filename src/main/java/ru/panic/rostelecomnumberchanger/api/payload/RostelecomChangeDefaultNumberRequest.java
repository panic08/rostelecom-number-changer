package ru.panic.rostelecomnumberchanger.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RostelecomChangeDefaultNumberRequest {
    private long serviceId;

    private String numberId;

    @JsonProperty("client_uuid")
    private String clientUuid;

    @JsonProperty("current_page")
    private String currentPage;

    private Object mask;

    private String numberType;

    @JsonProperty("page_uuid")
    private String pageUuid;
}
