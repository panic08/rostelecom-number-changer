package ru.panic.rostelecomnumberchanger.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.panic.rostelecomnumberchanger.api.payload.*;

@Component
@RequiredArgsConstructor
public class RostelecomApi {
    private final String ROSTELECOM_API_URL = "https://lk-api.rt.ru";

    private final RestTemplate restTemplate;

    public RostelecomGetAccountServicesMainInfoResponse getAccountServicesMainInfo(RostelecomGetAccountServicesMainInfoRequest request, String cookieString) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Cookie", cookieString);

        HttpEntity<RostelecomGetAccountServicesMainInfoRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<RostelecomGetAccountServicesMainInfoResponse> response =
                restTemplate.exchange(ROSTELECOM_API_URL + "/client-api/getAccountServicesMainInfo",
                        HttpMethod.POST, requestEntity, RostelecomGetAccountServicesMainInfoResponse.class);

        return response.getBody();
    }

    public RostelecomGetDefaultNumbersResponse getDefaultNumbers(RostelecomGetDefaultNumbersRequest request, String cookieString) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Cookie", cookieString);

        HttpEntity<RostelecomGetDefaultNumbersRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<RostelecomGetDefaultNumbersResponse> response =
                restTemplate.exchange(ROSTELECOM_API_URL + "/client-api/getDefNumbers",
                        HttpMethod.POST, requestEntity, RostelecomGetDefaultNumbersResponse.class);

        return response.getBody();
    }

    public void changeDefaultNumber(RostelecomChangeDefaultNumberRequest request, String cookieString) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Cookie", cookieString);

        HttpEntity<RostelecomChangeDefaultNumberRequest> requestEntity = new HttpEntity<>(request, headers);

        restTemplate.exchange(ROSTELECOM_API_URL + "/client-api/changeDefNumber",
                        HttpMethod.POST, requestEntity, Void.class);
    }
}
