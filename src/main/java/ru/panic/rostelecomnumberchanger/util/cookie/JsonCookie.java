package ru.panic.rostelecomnumberchanger.util.cookie;

import lombok.Data;

@Data
public class JsonCookie {
    private String name;
    private String value;
    private String domain;
    private String path;
    private long expirationDate;
}
