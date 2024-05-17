package ru.panic.rostelecomnumberchanger.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "accounts_table")
@Builder
public class Account {
    @Id
    private Long id;

    @Column("account_id")
    private Long accountId;

    @Column("cookie_string")
    private String cookieString;

    @Column("json_cookie_string")
    private String jsonCookieString;

    @Column("dolphin_profile_id")
    private Long dolphinProfileId;
}
