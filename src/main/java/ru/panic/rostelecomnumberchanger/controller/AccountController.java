package ru.panic.rostelecomnumberchanger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.panic.rostelecomnumberchanger.dto.AccountDto;
import ru.panic.rostelecomnumberchanger.payload.CreateAccountRequest;
import ru.panic.rostelecomnumberchanger.payload.CreateAccountResponse;
import ru.panic.rostelecomnumberchanger.payload.GetJsonCookieStringResponse;
import ru.panic.rostelecomnumberchanger.payload.UpdateCookieStringRequest;
import ru.panic.rostelecomnumberchanger.service.AccountService;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> get(@PathVariable("id") long id) {
        return ResponseEntity.ok(accountService.get(id));
    }

    @GetMapping("/{id}/getJsonCookieString")
    public ResponseEntity<GetJsonCookieStringResponse> getJsonCookieString(@PathVariable("id") long id) {
        return ResponseEntity.ok(accountService.getJsonCookieString(id));
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponse> create(@RequestBody CreateAccountRequest createAccountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.create(createAccountRequest));
    }

    @PatchMapping("/updateCookieString")
    public ResponseEntity<Void> updateCookieString(@RequestBody UpdateCookieStringRequest updateCookieStringRequest) {
        accountService.updateCookieString(updateCookieStringRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        accountService.delete(id);
        return ResponseEntity.ok().build();
    }
}
