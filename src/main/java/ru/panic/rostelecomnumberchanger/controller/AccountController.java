package ru.panic.rostelecomnumberchanger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.panic.rostelecomnumberchanger.payload.CreateAccountRequest;
import ru.panic.rostelecomnumberchanger.payload.CreateAccountResponse;
import ru.panic.rostelecomnumberchanger.payload.UpdateCookieStringRequest;
import ru.panic.rostelecomnumberchanger.service.AccountService;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<CreateAccountResponse> create(@RequestBody CreateAccountRequest createAccountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.create(createAccountRequest));
    }

    @PatchMapping("/updateCookieString")
    public ResponseEntity<Void> updateCookieString(@RequestBody UpdateCookieStringRequest updateCookieStringRequest) {
        accountService.updateCookieString(updateCookieStringRequest);

        return ResponseEntity.ok().body(null);
    }
}
