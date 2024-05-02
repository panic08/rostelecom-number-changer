package ru.panic.rostelecomnumberchanger.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.panic.rostelecomnumberchanger.api.payload.DolphinAntyGetCookiesResponse;
import ru.panic.rostelecomnumberchanger.api.payload.RostelecomGetAccountInfoRequest;

@Component
@RequiredArgsConstructor
public class DolphinAntyApi {
    private final RestTemplate restTemplate;

    @Value("${local.dolphin-anty.url}")
    private String LOCAL_DOLPHIN_ANTY_URL;

    @Value("${dolphin-antry.api-token}")
    private String dolhpinAntyApiToken;
    private final String SYNC_DOLPHIN_ANTY_URL = "https://sync.dolphin-anty-api.com";

    public void startBrowserProfile(long profileId, int automation) {
        restTemplate.postForEntity(LOCAL_DOLPHIN_ANTY_URL + "/v1.0/browser_profiles/" + profileId + "/start?automation=" + automation,
                null, Void.class);
    }

    public void stopBrowserProfile(long profileId) {
        restTemplate.getForEntity(LOCAL_DOLPHIN_ANTY_URL + "/v1.0/browser_profiles/" + profileId + "/stop",
                Void.class);
    }

    public DolphinAntyGetCookiesResponse getCookies(long profileId) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + dolhpinAntyApiToken);

        HttpEntity<DolphinAntyGetCookiesResponse> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<DolphinAntyGetCookiesResponse> dolphinAntyGetCookiesResponseResponseEntity =
                restTemplate.exchange(SYNC_DOLPHIN_ANTY_URL + "?actionType=getCookies&browserProfileId=" + profileId,
                        HttpMethod.GET, requestEntity, DolphinAntyGetCookiesResponse.class);

        return dolphinAntyGetCookiesResponseResponseEntity.getBody();
    }
}
