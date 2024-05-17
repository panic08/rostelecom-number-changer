package ru.panic.rostelecomnumberchanger.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountDto {
    private Long id;

    private Long accountId;

    private String cookieString;

    private String jsonCookieString;

    private Long dolphinProfileId;
}
