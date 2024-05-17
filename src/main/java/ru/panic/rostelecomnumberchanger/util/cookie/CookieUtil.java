package ru.panic.rostelecomnumberchanger.util.cookie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class CookieUtil {
    private final ObjectMapper objectMapper;

    public String extractDomain(String jsonCookieString,
                                List<String> domainSkipStrings) {
        Set<JsonCookie> jsonCookieSet;
        Set<JsonCookie> jsonCookieForDeleteSet = new HashSet<>();

        try {
            jsonCookieSet = objectMapper.readValue(jsonCookieString, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.warn(e.getMessage());

            throw new RuntimeException(e.getMessage());
        }

        for (JsonCookie jsonCookie : jsonCookieSet) {
            for (String domainSkipString : domainSkipStrings) {
                if (jsonCookie.getDomain().contains(domainSkipString)) {
                    jsonCookieForDeleteSet.add(jsonCookie);
                }
            }
        }

        jsonCookieSet.removeAll(jsonCookieForDeleteSet);

        try {
            return objectMapper.writeValueAsString(jsonCookieSet);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractName(String jsonCookieString,
                                List<String> nameSkipStrings) {
        Set<JsonCookie> jsonCookieSet;
        Set<JsonCookie> jsonCookieForDeleteSet = new HashSet<>();

        try {
            jsonCookieSet = objectMapper.readValue(jsonCookieString, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.warn(e.getMessage());

            throw new RuntimeException(e.getMessage());
        }

        for (JsonCookie jsonCookie : jsonCookieSet) {
            for (String nameSkipString : nameSkipStrings) {
                if (jsonCookie.getName().contains(nameSkipString)) {
                    jsonCookieForDeleteSet.add(jsonCookie);
                }
            }
        }

        jsonCookieSet.removeAll(jsonCookieForDeleteSet);

        try {
            return objectMapper.writeValueAsString(jsonCookieSet);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String convertFromJsonToRequest(String jsonCookieString) {
        StringBuilder defaultCookieStringBuilder = new StringBuilder();
        Set<JsonCookie> jsonCookieSet;

        try {
            jsonCookieSet = objectMapper.readValue(jsonCookieString, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.warn(e.getMessage());

            return null;
        }

        int i = 0;

        for (JsonCookie jsonCookie : jsonCookieSet) {
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
