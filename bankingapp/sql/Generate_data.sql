INSERT INTO users (username, password, first_name, last_name, email, role) VALUES 
  ('johnDoe', 'password123', 'John', 'Doe', 'john.doe@example.com', 'USER'),
  ('janeSmith', 'securepassword', 'Jane', 'Smith', 'jane.smith@example.com', 'USER'),
  ('mikeJohnson', '123456', 'Mike', 'Johnson', 'mike.johnson@example.com', 'USER'),
  ('amyLee', 'qwerty', 'Amy', 'Lee', 'amy.lee@example.com', 'USER'),
  ('timJohnson', 'password', 'Tim', 'Johnson', 'tim.johnson@example.com', 'USER'),
  ('admin', 'admin', 'Administrator', 'Administrator', 'administrator@example.com', 'ADMIN');


INSERT INTO accounts (account_number, account_type, balance, user_id) VALUES 
  ('100001', 'CHECKING', 5000.00, 1),
  ('100002', 'SAVINGS', 10000.00, 1),
  ('100003', 'CHECKING', 2500.00, 2),
  ('100004', 'SAVINGS', 15000.00, 2),
  ('100005', 'CHECKING', 500.00, 3),
  ('100006', 'SAVINGS', 2000.00, 3),
  ('100007', 'CHECKING', 7500.00, 1),
  ('100008', 'SAVINGS', 12000.00, 3),
  ('100009', 'CHECKING', 1000.00, 2),
  ('100010', 'SAVINGS', 5000.00, 1);
  
INSERT INTO transactions (transaction_date, transaction_type, amount, account_id)
VALUES ('2023-01-01 12:00:00', 'DEPOSIT', 1000.00, 2),
  ('2023-01-02 12:00:00', 'WITHDRAWAL', 500.00, 1),
  ('2023-01-03 12:00:00', 'DEPOSIT', 750.00, 4),
  ('2023-01-04 12:00:00', 'TRANSFER', 250.00, 5),
  ('2023-01-05 12:00:00', 'WITHDRAWAL', 1500.00, 3),
  ('2023-01-06 12:00:00', 'TRANSFER', 750.00, 2),
  ('2023-01-07 12:00:00', 'TRANSFER', 500.00, 1),
  ('2023-01-08 12:00:00', 'DEPOSIT', 250.00, 4);