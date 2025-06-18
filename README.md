# Security-SpringBoot-Basic 🔐

A Spring Boot application demonstrating basic authentication with support for both:

- **In‑memory user store** using `InMemoryUserDetailsManager`.  
- **JDBC authentication** using Spring Security’s default schema, along with custom tables.

---

## 📋 Features

- Supports authentication via in‑memory users (for quick testing).
- Supports JDBC-backed authentication using Spring Security’s default tables (`users`, `authorities`) and your own custom tables.
- Secure endpoints based on roles (e.g., `ROLE_USER`, `ROLE_ADMIN`).

---

## 🛠️ Prerequisites

- Java 17+ (or 11)
- Maven 3.5+
- A relational database (H2 / PostgreSQL / MySQL / etc.) set up and running
- [Postman](https://www.postman.com/downloads/) (for API testing)

---

## Password encoding strategies 

It also explains password encoding strategies like:

noop – for quick local testing ({noop}password)

bcrypt – the recommended way for storing passwords securely in production.
To convert text to Bcrypt I used [Bcrypt Generator](https://www.bcryptcalculator.com/).

## 🔧 Setup & Configuration

1. **Clone the repository**

    ```bash
    git clone https://github.com/saibhargav‑t/Security‑SpringBoot‑Basic.git
    cd Security‑SpringBoot‑Basic
    ```

2. **Configure database:**

   In `src/main/resources/application.properties` add your DB settings. For example (MySQL):

```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/mydb
   spring.datasource.username=myuser
   spring.datasource.password=mypass

   spring.jpa.hibernate.ddl-auto=update
   spring.datasource.initialization-mode=always
```

---

## 🧠 Tips & Hints

- Spring Security auto-creates default schema for JDBC if spring.datasource.initialization-mode=always.

- Use Base64(username:password) for Basic Auth headers.
  
---

## 📝 Customize for Your Project

- Replace `/api/*` and /user/* paths with your actual endpoints.

- Add `/role/**` checks or antMatchers("/admin/**").hasRole("ADMIN") as needed.

- Secure method-level access with `@PreAuthorize`.

---

## Contact

If you hav any issue or questions please raise a issue or contact me at :
  
- [Mail](mailto:bhargavindian@outlook.com)
- [LinkedIn](https://www.linkedin.com/in/saibhargav-t/)

---

## Thank You :)