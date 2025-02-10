-- create table "account"
CREATE TABLE account (
    id BIGSERIAL PRIMARY KEY,
    version int NOT NULL,
    balance DECIMAL(19,2) NOT NULL DEFAULT 0.00
);

-- create table "statement"
CREATE TABLE statement (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    operation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount DECIMAL(19,2) NOT NULL,
    balance DECIMAL(19,2) NOT NULL,
    operation_type VARCHAR(50) NOT NULL,
    FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE
);

-- add index for the performances
CREATE INDEX idx_account_id ON statement(account_id);
