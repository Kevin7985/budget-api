CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    login VARCHAR(255),
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id BIGINT NOT NULL,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    valute VARCHAR(10) NOT NULL,
    amount DOUBLE PRECISION DEFAULT 0 NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (id),
    CONSTRAINT uq_account_title UNIQUE(title),
    CONSTRAINT fk_user_for_account FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title VARCHAR(50) NOT NULL,
    operation_type VARCHAR(50) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS operations (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    account_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    operation_type VARCHAR(50) NOT NULL,
    amount DOUBLE PRECISION DEFAULT 0 NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_operation PRIMARY KEY (id),
    CONSTRAINT fk_operation_for_account FOREIGN KEY (account_id) REFERENCES accounts (id) ON DELETE RESTRICT,
    CONSTRAINT fk_operation_for_category FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE RESTRICT
);