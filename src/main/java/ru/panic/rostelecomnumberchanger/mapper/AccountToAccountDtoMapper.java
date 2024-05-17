package ru.panic.rostelecomnumberchanger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.panic.rostelecomnumberchanger.dto.AccountDto;
import ru.panic.rostelecomnumberchanger.model.Account;

@Mapper(componentModel = "spring")
public interface AccountToAccountDtoMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "accountId", source = "accountId"),
            @Mapping(target = "cookieString", source = "cookieString"),
            @Mapping(target = "jsonCookieString", source = "jsonCookieString"),
            @Mapping(target = "dolphinProfileId", source = "dolphinProfileId")
    })
    AccountDto accountToAccountDto(Account account);
}
