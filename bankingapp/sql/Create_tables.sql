drop table if exists users cascade; 
drop table if exists accounts cascade;
drop table if exists transactions cascade;


CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
  id SERIAL PRIMARY KEY,
  account_number VARCHAR(20) NOT NULL UNIQUE,
  account_type VARCHAR(10) NOT NULL,
  balance DECIMAL(10,2) NOT NULL,
  user_id INTEGER REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS transactions (
  id SERIAL PRIMARY KEY,
  transaction_date TIMESTAMP NOT NULL,
  transaction_type VARCHAR(10) NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  account_id INTEGER REFERENCES accounts(id)
);
