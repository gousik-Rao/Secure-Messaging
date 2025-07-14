# Secure Messaging

> **A secure and robust platform for encrypted, real-time messaging and secure file sharing, designed for high-security environments like military or confidential organizational communication.**

---

## 🚀 Overview

**Secure Messaging** is a Java (Spring Boot) web application providing end-to-end encrypted communication, secure file sharing, and real-time collaboration tools. Built with user-friendliness and strong security in mind, it ensures sensitive information remains private and safe.

---

## ✨ Features

- **End-to-end encryption** of messages
- **Real-time messaging** between users
- **Secure file sharing**
- **Collaborative planning tools**
- **Detailed reporting and analytics**
- **Inbox and message management** (read, delete, search, filter)
- **User profile & contact management**
- **Real-time notifications**
- **Robust authentication and authorization (JWT, BCrypt)**

---

## 🛡️ Security

- **Encrypted Messages:** All message data is encrypted using AES before storage/transmission.
- **Role-based Access:** Secure endpoints and user actions via Spring Security with JWT authentication.
- **Password Security:** Passwords are hashed and stored using BCrypt.

---

## 🏗️ Tech Stack

- **Backend:** Java, Spring Boot, Spring Data JPA, Spring Security (JWT, BCrypt)
- **Frontend:** HTML5, CSS3 (Thymeleaf templates)
- **Database:** (Typically H2, MySQL, or PostgreSQL—configure as needed)
- **Other:** JPA Specification API for advanced search/filter, Maven for builds

---

## 🖥️ Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- A supported database (H2 for testing, or configure MySQL/Postgres)

### Setup & Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/gousik-Rao/Secure-Messaging.git
   cd Secure-Messaging
   ```

2. **Configure the database**
   - By default, uses H2 in-memory DB for quick start.
   - To use MySQL/Postgres, edit `src/main/resources/application.properties` with your DB settings.

3. **Build & Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the app**
   - Open your browser at `http://localhost:8080`

---

## 📝 Usage

- **Messaging:** Send and receive encrypted messages from the "Messages" section.
- **Inbox:** View received messages, mark as read/unread, or delete.
- **Search:** Use filters to search messages by keyword, date, or status.
- **Contacts:** Add/manage your contacts securely.
- **Notifications:** Get real-time updates on incoming messages and system alerts.
- **Profile:** Change your password and manage account settings.

---

## 📂 Project Structure

```
src/
 └── main/
      ├── java/com/military/
      │     ├── Config/           # Security config (JWT, BCrypt)
      │     ├── Controller/       # REST endpoints
      │     ├── DTO/              # Data transfer objects
      │     ├── Entity/           # JPA entities (Message, MessageHistory, etc.)
      │     ├── Encryption/       # Encryption utilities (AES)
      │     ├── Repository/       # Spring Data JPA repositories
      │     └── Service/          # Business logic (MessageService, etc.)
      └── resources/
            ├── templates/        # HTML pages (Thymeleaf)
            └── application.properties # App config
```

---

## 🔒 Example: Sending a Secure Message

1. The sender writes a message.
2. The message content is encrypted with AES (see `EncryptionUtility`).
3. The encrypted message is stored in the database.
4. Only the intended recipient (after authentication) can decrypt and read the message.

---

## 🤝 Contributing

Pull requests and suggestions are welcome! Please open an issue first to discuss your ideas or report bugs.

---

## 📄 License

This project is licensed under the terms of your choice.

---

## 🙌 Credits

Developed by [Gousik Rao](https://github.com/gousik-Rao)

---
