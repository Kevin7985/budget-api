CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    valute VARCHAR(10) NOT NULL,
    amount DOUBLE PRECISION DEFAULT 0 NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (id),
    CONSTRAINT uq_account_title UNIQUE(title)
);

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title VARCHAR(50) NOT NULL,
    operation_type VARCHAR(50) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);