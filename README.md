# Banking Backend Application

A Spring Boot–based banking backend application that provides core banking functionalities such as account management, transactions, fixed deposits, card applications, authentication, and system health monitoring.

Build Backend using Springboot, Hibernate (MySql), Log4J, Jwt, Exception Handling

## 🚀 Features
- User authentication using JWT
- Account creation and management
- Fund transfer (IMPS)
- Transaction history
- Fixed Deposit management with scheduler
- Card application and status tracking
- Global exception handling
- System health monitoring

## 🛠 Tech Stack
- Java 17
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- Maven
- H2 / MySQL (configurable)

## 📂 Project Structure
src/main/java/com/bankapp
├── account
├── auth
├── card
├── fd
├── monitoring
├── security
├── transaction
└── exception

## ▶️ How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/shrutitalukder/banking-backend.git

   Navigate to the project:

cd banking-backend

Run the application:
====================

./mvnw spring-boot:run

🔐 Authentication
========================

Login API returns a JWT token

Use the token in Authorization header:

Authorization: Bearer <token>

📌 Future Enhancements
===============================

Role-based access control (ADMIN / USER)

Database integration with PostgreSQL

Docker support

API documentation using Swagger

Postman Testing
===========================

1st case (view all accounts)
===============================

http://localhost:8090/api/accounts

Authorization: Bearer <your_jwt_token>

mysql
**********
INSERT INTO accounts (account_number, account_type, balance, username)
VALUES
('ACC1001', 'SAVINGS', 50000, 'shruti'),
('ACC1002', 'CURRENT', 120000, 'shruti');

Logging
**************

POST

http://localhost:8090/api/auth/login

json
*************
{
  "username": "shruti",
  "password": "password"
}

2nd case (IMPS Transfer)
===========================

POST

http://localhost:8090/api/transactions/imps

Go to Headers tab and add:

Key	Value
Authorization	Bearer <PASTE_JWT_TOKEN>
Content-Type	application/json

json
*********
{
  "fromAccount": "ACC1001",
  "toAccount": "ACC1002",
  "amount": 5000
}

mysql
**********
USE banking_db;

SELECT account_number, balance FROM accounts;
SELECT * FROM transactions;

3rd case (Track Card Application Status)
=========================================

POST

http://localhost:8090/api/cards/apply?cardType=CREDIT

GET

http://localhost:8090/api/cards/status

mysql
**************
INSERT INTO card_applications
    -> (card_type, status, applied_at, username)
    -> VALUES
    -> ('DEBIT', 'APPLIED', NOW(), 'shruti'),
    -> ('CREDIT', 'APPROVED', NOW(), 'shruti');

4th case (Auto-Renew Scheduler)
==================================

no need for postman testing -> only log file and databse level testing

mysql
************
INSERT INTO fixed_deposits
    -> (username, amount, tenure_months, start_date, maturity_date, auto_renew, status)
    -> VALUES
    -> ('shruti', 50000, 6, CURDATE(), CURDATE(), true, 'ACTIVE');

SELECT * FROM fixed_deposits;

5th case (System Health & Monitoring Hooks (ADMIN & OPS))
===============================================================

GET

http://localhost:8090/actuator/health

GET

http://localhost:8090/actuator/metrics

User
---------------

POST /api/auth/login

{
  "username": "shruti",
  "password": "password123"
}

Can access /api/accounts, /api/cards, /api/transactions
Cannot access /actuator/health

Admin
------------------

POST /api/auth/login

{
  "username": "admin",
  "password": "admin123"
}

Can access /actuator/health
Can monitor system



Author: Shruti Talukder
