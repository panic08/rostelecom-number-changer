CREATE TABLE IF NOT EXISTS accounts_table(
    id SERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    cookie_string TEXT NOT NULL,
    json_cookie_string TEXT NOT NULL,
    dolphin_profile_id VARCHAR(255) NOT NULL
);