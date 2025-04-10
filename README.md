# Spring-AuthSystem
# User Authentication System with Spring Boot & Spring Security

This project implements a **complete and secure user authentication system** using **Spring Boot** and **Spring Security**. The system includes **user registration**, **login**, and **role-based authorization** for different pages such as **Admin** and **User** dashboards.

## Features

- **User Registration**: Users can register by providing necessary details and choose their roles (USER / ADMIN).
- **Secure Login**: Users can log in securely using **Spring Security**, with passwords encrypted using **BCrypt**.
- **Role-based Authorization**: Different user roles (Admin / User) are used to redirect users to their respective dashboards.
  - **ROLE_ADMIN**: Redirected to `/admin-page`.
  - **ROLE_USER**: Redirected to `/user-page`.
- **Session Management**: Users can log out securely, which invalidates their session and redirects them back to the login page.
- **Custom Pages**: 
  - **Login Page**: Custom login page built using **Thymeleaf**.
  - **Registration Page**: Custom registration page for creating new users.
- **BCrypt Password Encryption**: Passwords are stored securely with **BCrypt** encryption.
- **Admin and User Dashboards**: Role-specific dashboards for Admins and Users, accessible only by the corresponding roles.
  
## Technologies Used

- **Java 17+**
- **Spring Boot**: For backend development and handling REST APIs.
- **Spring Security**: For implementing authentication and role-based authorization.
- **Thymeleaf**: For creating dynamic web pages.
- **MySQL / H2 Database**: For storing user data and roles (MySQL for production, H2 for testing).
- **Maven**: For project management and dependencies.

## How It Works

### 1. **Register**: 
Users can register using the custom registration page. The registration form includes fields for the username, password, and role.

### 2. **Login**: 
Once users have registered, they can log in using their credentials (username and password). After logging in, the system checks the user’s role.

### 3. **Redirection**: 
- **ROLE_ADMIN** users are redirected to `/admin-page`.
- **ROLE_USER** users are redirected to `/user-page`.

### 4. **Security**: 
Passwords are securely stored using **BCrypt** encryption, ensuring sensitive data is never exposed.

### 5. **Session Management**: 
After logging out, the session is invalidated, and users are redirected back to the login page.

## Getting Started

To run this project locally, follow these steps:

### Prerequisites

- **Java 17+** installed.
- **Maven** installed.
- **MySQL** or **H2 Database** (H2 is configured for development by default).

### Steps to run:

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/user-authentication-system.git
