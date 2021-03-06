DROP TABLE IF EXISTS cars CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS client_accounts CASCADE;
DROP TABLE IF EXISTS managers_accounts CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

CREATE TABLE IF NOT EXISTS users (
  user_id SERIAL PRIMARY KEY,
  login VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS roles (
  role_id SERIAL PRIMARY KEY,
  role VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS client_accounts (
  client_account_id SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL UNIQUE,
  last_name VARCHAR(255) NOT NULL UNIQUE,
  telephone VARCHAR(10),
  user_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS manager_accounts (
  manager_account_id SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL UNIQUE,
  last_name VARCHAR(255) NOT NULL UNIQUE,
  telephone VARCHAR(10),
  user_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
  order_id SERIAL PRIMARY KEY,
  car_id BIGINT NOT NULL,
  client_account_id BIGINT NOT NULL,
  description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cars (
  car_id SERIAL PRIMARY KEY,
  vin VARCHAR(255) NOT NULL UNIQUE,
  brand VARCHAR(255) NOT NULL,
  model VARCHAR(255) NOT NULL,
  production_year VARCHAR(255) NOT NULL,
  registration_number VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  client_account_id BIGINT NOT NULL
);

ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles(role_id);
ALTER TABLE orders ADD FOREIGN KEY (client_account_id) REFERENCES client_accounts(client_account_id);
ALTER TABLE orders ADD FOREIGN KEY (car_id) REFERENCES cars(car_id);
ALTER TABLE client_accounts ADD FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE;
ALTER TABLE manager_accounts ADD FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE;
ALTER TABLE cars ADD FOREIGN KEY (client_account_id) REFERENCES client_accounts(client_account_id) ON DELETE CASCADE;