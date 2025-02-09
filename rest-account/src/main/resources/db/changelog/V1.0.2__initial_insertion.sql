-- Insertion account in table "account"
INSERT INTO account (balance) VALUES (1000.00);
INSERT INTO account (balance) VALUES (500.00);
INSERT INTO account (balance) VALUES (300.00);

-- Insertion in table "statement"

INSERT INTO statement (account_id, amount, balance, operation_type)
VALUES (1, 500.00, 1500.00, 'DEPOSIT');

INSERT INTO statement (account_id, amount, balance, operation_type)
VALUES (1, 200.00, 1300.00, 'WITHDRAWAL');

INSERT INTO statement (account_id, amount, balance, operation_type)
VALUES (2, 300.00, 800.00, 'DEPOSIT');

INSERT INTO statement (account_id, amount, balance, operation_type)
VALUES (2, 100.00, 700.00, 'WITHDRAWAL');

INSERT INTO statement (account_id, amount, balance, operation_type)
VALUES (3, 200.00, 500.00, 'DEPOSIT');

INSERT INTO statement (account_id, amount, balance, operation_type)
VALUES (3, 50.00, 450.00, 'WITHDRAWAL');