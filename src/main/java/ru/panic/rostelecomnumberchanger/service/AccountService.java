package ru.panic.rostelecomnumberchanger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.panic.rostelecomnumberchanger.dto.AccountDto;
import ru.panic.rostelecomnumberchanger.mapper.AccountToAccountDtoMapperImpl;
import ru.panic.rostelecomnumberchanger.model.Account;
import ru.panic.rostelecomnumberchanger.payload.CreateAccountRequest;
import ru.panic.rostelecomnumberchanger.payload.CreateAccountResponse;
import ru.panic.rostelecomnumberchanger.payload.GetJsonCookieStringResponse;
import ru.panic.rostelecomnumberchanger.payload.UpdateCookieStringRequest;
import ru.panic.rostelecomnumberchanger.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountToAccountDtoMapperImpl accountToAccountDtoMapper;

    public AccountDto get(long id) {
        return accountToAccountDtoMapper.accountToAccountDto(accountRepository.findById(id)
                .orElseThrow());
    }

    public GetJsonCookieStringResponse getJsonCookieString(long id) {
        return GetJsonCookieStringResponse.builder()
                .jsonCookieString(accountRepository.findJsonCookieStringById(id)
                        .orElse(null))
                .build();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CreateAccountResponse create(CreateAccountRequest createAccountRequest) {
        String cookieString = createAccountRequest.getCookieString();

        Account newAccount = accountRepository.save(Account.builder()
                .accountId(createAccountRequest.getAccountId())
                .cookieString(cookieString)
                .dolphinProfileId(createAccountRequest.getDolphinProfileId())
                .build());

        return CreateAccountResponse.builder()
                .id(newAccount.getId())
                .build();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateCookieString(UpdateCookieStringRequest updateCookieStringRequest) {
        accountRepository.updateCookieStringById(updateCookieStringRequest.getCookieString(), updateCookieStringRequest.getId());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(long id) {
        accountRepository.deleteById(id);
    }
}
