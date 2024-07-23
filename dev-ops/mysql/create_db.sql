CREATE DATABASE  IF NOT EXISTS rabbitmq_test_db; 
USE rabbitmq_test_db;

CREATE TABLE IF NOT EXISTS User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO User (username, email) VALUES
('Docker User 1', 'docker@hub.com'),
('Docker User 2', 'docker@hub.com'),
('Docker User 3', 'docker@hub.com'),
('Docker User 4', 'docker@hub.com'),
('Docker User 5', 'docker@hub.com');