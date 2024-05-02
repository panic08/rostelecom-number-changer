package ru.panic.rostelecomnumberchanger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.panic.rostelecomnumberchanger.payload.NumberChangerChangeResponse;
import ru.panic.rostelecomnumberchanger.service.NumberChangerService;

@RestController
@RequestMapping("/api/v1/numberChanger")
@RequiredArgsConstructor
public class NumberChangerController {
    private final NumberChangerService numberChangerService;

    @PostMapping("/rostelecom/change")
    public ResponseEntity<NumberChangerChangeResponse> rostelecomChange(@RequestParam("account_key") String accountKey,
                                                                        @RequestParam("number_index") int numberIndex) {
        return ResponseEntity.ok(numberChangerService.rostelecomChange(accountKey, numberIndex));
    }
}
