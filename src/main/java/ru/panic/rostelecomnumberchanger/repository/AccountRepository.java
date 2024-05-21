package ru.panic.rostelecomnumberchanger.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.panic.rostelecomnumberchanger.model.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query("UPDATE accounts_table SET cookie_string = :cookieString WHERE id = :id")
    @Modifying
    void updateCookieStringById(@Param("cookieString") String cookieString, @Param("id") long id);

    @Query("SELECT a.json_cookie_string FROM accounts_table a WHERE a.id = :id")
    Optional<String> findJsonCookieStringById(@Param("id") long id);

    @Query("SELECT a.* FROM accounts_table a ORDER BY a.id DESC")
    List<Account> findAllOrderByIdDesc();
}
