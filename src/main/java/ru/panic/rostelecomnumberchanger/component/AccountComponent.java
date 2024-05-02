package ru.panic.rostelecomnumberchanger.component;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class AccountComponent {
    private final Map<String, Account> keyAccountMap = new HashMap<>();

    @Getter
    @Setter
    @Builder
    public static class Account {
        private Long accountId;
        private String cookieString;
        private Long dolphinProfileId;
    }
}
