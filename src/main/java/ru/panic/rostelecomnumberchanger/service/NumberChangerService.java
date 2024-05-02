package ru.panic.rostelecomnumberchanger.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.panic.rostelecomnumberchanger.api.RostelecomApi;
import ru.panic.rostelecomnumberchanger.api.payload.*;
import ru.panic.rostelecomnumberchanger.component.AccountComponent;
import ru.panic.rostelecomnumberchanger.payload.NumberChangerChangeResponse;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class NumberChangerService {
    private final AccountComponent accountComponent;
    private final RostelecomApi rostelecomApi;

    public NumberChangerChangeResponse rostelecomChange(String accountKey, int numberIndex) {
        AccountComponent.Account rostelecomAccount = accountComponent.getKeyAccountMap().get(accountKey);

        long rostelecomAccountId = rostelecomAccount.getAccountId();
        String rostelecomCookieString = rostelecomAccount.getCookieString();

        RostelecomGetAccountServicesMainInfoResponse rostelecomGetAccountServicesMainInfoResponse =
                rostelecomApi.getAccountServicesMainInfo(RostelecomGetAccountServicesMainInfoRequest.builder()
                        .accountId(rostelecomAccountId)
                        .clientUuid("7750992C-265A-43CC-98BA-CC7AAD47BC69")
                        .currentPage("account")
                        .pageUuid("59B74B2B-CA0D-4752-96DF-77F10BC8DDFC")
                .build(), rostelecomCookieString);


        Long rostelecomNumberChangeServiceId = null;

        for (Map.Entry<String, RostelecomGetAccountServicesMainInfoResponse.AccountMainInfo> entry : rostelecomGetAccountServicesMainInfoResponse.getService().entrySet()) {
            rostelecomNumberChangeServiceId = Long.valueOf(entry.getKey());
            break;
        }

        RostelecomGetDefaultNumbersResponse rostelecomGetDefaultNumbersResponse =
                rostelecomApi.getDefaultNumbers(RostelecomGetDefaultNumbersRequest.builder()
                                .serviceId(rostelecomNumberChangeServiceId)
                                .clientUuid("7750992C-265A-43CC-98BA-CC7AAD47BC69")
                                .currentPage("def_number")
                                .mask(null)
                                .numberType("SIMPLE")
                                .pageUuid("49D5EAD2-461B-4E36-9993-B01D6991250D")
                        .build(), rostelecomCookieString);

        String rostelecomNumberChangeNumberId = rostelecomGetDefaultNumbersResponse.getNumbers().get(numberIndex - 1).getId();
        String rostelecomNumberChangePhoneNumber = rostelecomGetDefaultNumbersResponse.getNumbers().get(numberIndex - 1).getPhoneNumber();

        rostelecomApi.changeDefaultNumber(RostelecomChangeDefaultNumberRequest.builder()
                        .serviceId(rostelecomNumberChangeServiceId)
                        .numberId(rostelecomNumberChangeNumberId)
                        .clientUuid("7750992C-265A-43CC-98BA-CC7AAD47BC69")
                        .currentPage("def_number")
                        .mask(null)
                        .numberType("SIMPLE")
                        .pageUuid("49D5EAD2-461B-4E36-9993-B01D6991250D")
                .build(), rostelecomCookieString);


        long currentTimestamp = System.currentTimeMillis();

        loop1: while (System.currentTimeMillis() - currentTimestamp <= 25000) {
            rostelecomApi.getAccountInfo(RostelecomGetAccountInfoRequest.builder()
                    .accountId(rostelecomAccountId)
                    .clientUuid("7750992C-265A-43CC-98BA-CC7AAD47BC69")
                    .currentPage("account")
                    .pageUuid("59B74B2B-CA0D-4752-96DF-77F10BC8DDFC")
                    .build(), rostelecomCookieString);

            RostelecomGetAccountServicesMainInfoResponse rostelecomGetAccountServicesMainInfoResponse1 =
                    rostelecomApi.getAccountServicesMainInfo(RostelecomGetAccountServicesMainInfoRequest.builder()
                            .accountId(rostelecomAccountId)
                            .clientUuid("7750992C-265A-43CC-98BA-CC7AAD47BC69")
                            .currentPage("account")
                            .pageUuid("59B74B2B-CA0D-4752-96DF-77F10BC8DDFC")
                            .build(), rostelecomCookieString);

            if (rostelecomGetAccountServicesMainInfoResponse1.getService() == null || rostelecomGetAccountServicesMainInfoResponse1.getService().isEmpty()) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    log.warn(e.getMessage());
                }

                continue;
            }

            for (Map.Entry<String, RostelecomGetAccountServicesMainInfoResponse.AccountMainInfo> entry : rostelecomGetAccountServicesMainInfoResponse1.getService().entrySet()) {
                String accountMainInfoPurePhoneNumber = cleanPhoneNumber(entry.getValue().getPhoneNumber());

                if (accountMainInfoPurePhoneNumber.equals(rostelecomNumberChangePhoneNumber)) {
                    return NumberChangerChangeResponse.builder().phoneNumber(accountMainInfoPurePhoneNumber).build();
                } else {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        log.warn(e.getMessage());
                    }
                    continue loop1;
                }
            }
        }

        return NumberChangerChangeResponse.builder().phoneNumber(null).build();
    }

    private String cleanPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(phoneNumber);

        StringBuilder cleanedNumberBuilder = new StringBuilder();

        while (matcher.find()) {
            cleanedNumberBuilder.append(matcher.group());
        }

        return cleanedNumberBuilder.toString();
    }
}
