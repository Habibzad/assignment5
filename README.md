# Banking App

This Banking app is a simple but secure and user-friendly financial platform built with Java, Spring Boot, Spring JDBC, Spring Security, and Slf4j for logging, and backed by a PostgreSQL database. It offers a range of essential features, including account creation, money transfers between accounts, and access to various Certificate of Deposit (CD) offerings. The system includes distinct user roles: regular account holders and admin users. Regular users can create accounts, transfer money, and view their own account details, ensuring a personalized banking experience. Admin users, on the other hand, possess elevated privileges, enabling them to close accounts, create new users, and manage accounts and account holders. The application emphasizes robust security measures, employing Spring Security for authentication and authorization, safeguarding user data and transactions.
## Technologies Used
- **Spring Boot:** Provides a robust framework for building Java-based applications with minimal configuration.
- **Spring JDBC:** Simplifies database operations and eliminates the need for manual connection management.
- **Spring Security:** Offers authentication and authorization mechanisms to secure endpoints and resources.

## Database
The application uses **PostgreSQL** as the database management system to store account information and other relevant data.

## Functionality and Endpoints

## Security

- **User Authentication:** The application uses Spring Security to authenticate users.
    - **POST `/login`**: Users can authenticate by providing their credentials in the payload. For instance, an admin user can authenticate with the following payload:

        ```json
        {
          "username": "admin",
          "password": "admin"
        }
        ```
- **User Authorization:** Admin users have elevated privileges to manage accounts and users, while regular account holders can only view their own information.

## Admin Endpoints

### User Management

- **Get Users:**
  - **HTTP Method:** GET
  - **Path:** `/users/`
  - **Authentication:** Bearer Token
  - **Request Payload:** None
  - **Description:** Retrieves a list of users from the system. Requires authentication using a valid Bearer token. Admin users can access this endpoint to retrieve user information.
  - **Response body:**
    ```json
    {
      "users": [
          {
          "id": 1,
          "userName": "john_doe",
          "active": true,
          "roles": "ROLE_USER"
          },
          {
          "id": 2,
          "userName": "alice_smith",
          "active": true,
          "roles": "ROLE_USER"
          },
          {
          "id": 3,
          "userName": "bob_jones",
          "active": false,
          "roles": "ROLE_USER"
          }
      ]
    }
    ```

## Create User
- **HTTP Method:** POST
- **Path:** `/api/createuser`
- **Authentication:** Bearer Token
- **Request Payload:**
  ```json
  {
    "userName": "tom",
    "password": "tom",
    "active": true,
    "roles": "ROLE_USER"
  }
  ```
- **Description:** Creates a new user in the system with the provided user details. Requires authentication using a valid Bearer token. Admin users can use this endpoint to create new user accounts.

## Update User
- **HTTP Method:** PUT
- **Path:** `/api/users/{userId}`
- **Authentication:** Bearer Token
- **Request Payload:**
  ```json
  {
    "userName": "Lisa",
    "password": "Smith",
    "active": true,
    "roles": "ROLE_USER"
  }
  ```
- **Description:** Updates an existing user's information identified by the `userId` parameter. Requires authentication using a valid Bearer token. Admin users can use this endpoint to modify user details.

## Delete Users
- **HTTP Method:** DELETE
- **Path:** `/api/users/{userId}`
- **Authentication:** Bearer Token
- **Request Payload:**
  ```json
  {
    "id": 15
  }
  ```
- **Description:** Deletes a user from the system based on the `userId` parameter. Requires authentication using a valid Bearer token. Admin users can use this endpoint to remove user accounts from the system.

- **User and Account Creation:**
    - **POST `/api/admin/users/create`**: Admin users can create new users by specifying user details.
    - **POST `/api/admin/accounts/create`**: Admin users can create new accounts for users. They provide necessary details, and the system creates the account accordingly.

- **Account Holder Management:**
    - **GET `/api/account-holder/{accountHolderId}`**: Account holders can view their profile information.
    - **GET `/api/account-holder/{accountHolderId}/accounts`**: Account holders can view a list of their accounts.

### User Endpoints

1. **Account Creation:**
   - **POST `/api/accounts/create`**: Allows users to create a new bank account. Users provide necessary details, and the system creates the account accordingly.

2. **Money Transfer:**
   - **POST `/api/accounts/transfer`**: Enables users to transfer money between their accounts. Users specify source and destination accounts along with the transfer amount.

3. **CD Offerings:**
   - **GET `/api/cd-offerings`**: Provides a list of available CD (Certificate of Deposit) offerings to users. Users can view the available options for investing.




This simple banking app backend provides essential banking functionalities, ensuring secure transactions and user data privacy. It leverages modern technologies and best practices to deliver a seamless banking experience.
