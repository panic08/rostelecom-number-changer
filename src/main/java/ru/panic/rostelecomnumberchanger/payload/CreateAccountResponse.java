package ru.panic.rostelecomnumberchanger.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateAccountResponse {
    private String key;
}
