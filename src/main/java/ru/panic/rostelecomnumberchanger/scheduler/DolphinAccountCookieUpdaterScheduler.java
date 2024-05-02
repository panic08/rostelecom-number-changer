package ru.panic.rostelecomnumberchanger.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import ru.panic.rostelecomnumberchanger.api.DolphinAntyApi;
import ru.panic.rostelecomnumberchanger.api.payload.DolphinAntyGetCookiesResponse;
import ru.panic.rostelecomnumberchanger.component.AccountComponent;
import ru.panic.rostelecomnumberchanger.util.cookie.CookieUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DolphinAccountCookieUpdaterScheduler {
    private final AccountComponent accountComponent;
    private final DolphinAntyApi dolphinAntyApi;
    private final CookieUtil cookieUtil;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 5000)
    public void updateAccountCookie() {
        log.info("Starting updating account cookie");

        for (Map.Entry<String, AccountComponent.Account> entry : accountComponent.getKeyAccountMap().entrySet()) {
            AccountComponent.Account account = entry.getValue();

            dolphinAntyApi.startBrowserProfile(account.getDolphinProfileId(), 1);

            try {
                Thread.sleep(35000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }

            dolphinAntyApi.stopBrowserProfile(account.getDolphinProfileId());

            try {
                Thread.sleep(5500);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }

            DolphinAntyGetCookiesResponse dolphinAntyGetCookiesResponse =
                    dolphinAntyApi.getCookies(account.getDolphinProfileId());

            String jsonCookieString = null;

            try {
                jsonCookieString = objectMapper.writeValueAsString(dolphinAntyGetCookiesResponse.getData());
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }

            account.setCookieString(cookieUtil.convertFromJsonToDefaultWithSkips(jsonCookieString,
                    new ArrayList<>(List.of("yandex", "kaspersky", "mail", "google", "vk")),
                    new ArrayList<>(List.of("__zzatw-rt-lk"))));

            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }
}
