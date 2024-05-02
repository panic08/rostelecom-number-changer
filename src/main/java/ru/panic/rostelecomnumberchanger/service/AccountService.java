package ru.panic.rostelecomnumberchanger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.panic.rostelecomnumberchanger.component.AccountComponent;
import ru.panic.rostelecomnumberchanger.payload.CreateAccountRequest;
import ru.panic.rostelecomnumberchanger.payload.CreateAccountResponse;
import ru.panic.rostelecomnumberchanger.payload.UpdateCookieStringRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountComponent accountComponent;

    public CreateAccountResponse create(CreateAccountRequest createAccountRequest) {
        String key = UUID.randomUUID().toString();

        String cookieString = createAccountRequest.getCookieString();

        accountComponent.getKeyAccountMap().put(key, AccountComponent.Account.builder()
                .accountId(createAccountRequest.getAccountId())
                .cookieString(cookieString)
                .dolphinProfileId(createAccountRequest.getDolphinProfileId())
                .build());

        return CreateAccountResponse.builder()
                .key(key)
                .build();
    }

    public void updateCookieString(UpdateCookieStringRequest updateCookieStringRequest) {
        AccountComponent.Account currentAccount = accountComponent.getKeyAccountMap().get(updateCookieStringRequest.getKey());

        currentAccount.setCookieString(updateCookieStringRequest.getCookieString());

        accountComponent.getKeyAccountMap().put(updateCookieStringRequest.getKey(), currentAccount);
    }
}
