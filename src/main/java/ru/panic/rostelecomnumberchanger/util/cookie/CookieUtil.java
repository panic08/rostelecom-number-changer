package ru.panic.rostelecomnumberchanger.util.cookie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class CookieUtil {
    private final ObjectMapper objectMapper;

    public String convertFromJsonToDefaultWithSkips(String jsonCookieString,
                                                    List<String> domainSkipStrings,
                                                    List<String> nameSkipStrings) {
        StringBuilder defaultCookieStringBuilder = new StringBuilder();
        Set<JsonCookie> jsonCookieSet;

        try {
            jsonCookieSet = objectMapper.readValue(jsonCookieString, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.warn(e.getMessage());

            return null;
        }

        int i = 0;
        firstLoop: for (JsonCookie jsonCookie : jsonCookieSet) {
            for (String domainSkipString : domainSkipStrings) {
                if (jsonCookie.getDomain().contains(domainSkipString)) {
                    continue firstLoop;
                }
            }

            for (String nameSkipString : nameSkipStrings) {
                if (jsonCookie.getName().contains(nameSkipString)) {
                    continue firstLoop;
                }
            }

            if (i == jsonCookieSet.size() - 1) {
                defaultCookieStringBuilder.append(jsonCookie.getName()).append("=").append(jsonCookie.getValue());
            } else {
                defaultCookieStringBuilder.append(jsonCookie.getName()).append("=").append(jsonCookie.getValue()).append("; ");
            }

            i++;
        }

        return defaultCookieStringBuilder.toString();
    }
}
