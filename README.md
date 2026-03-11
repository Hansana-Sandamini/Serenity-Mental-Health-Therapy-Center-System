# Serenity Mental Health Therapy Center System

A **Java Hibernate-based management system** developed to digitalize and streamline the operations of the Serenity Mental Health Therapy Center. The system replaces the manual patient registration process and improves efficiency in managing therapists, therapy programs, therapy sessions, and payments.

---

## 📌 Project Overview

The Serenity Mental Health Therapy Center provides therapy programs for individuals seeking professional mental health support. With approximately **3,000 patients annually**, managing patient records manually became inefficient and error-prone.

This system was developed to automate the therapy center's operations and improve data management using **Hibernate ORM and Java**.

This project was developed as part of the **ORM Concepts coursework** in the **Graduate Diploma in Software Engineering program**.

---

## 🚀 System Features

### 🔐 User Role Management

The system supports authentication with role-based access.

**Admin**
- Manage therapists
- Manage therapy programs
- View reports

**Receptionist**
- Manage patient records
- Schedule therapy sessions
- Process payments
- Generate invoices

---

## ⚙️ Core Functionalities

### 👩‍⚕️ Therapist Management
- Add new therapists
- Update therapist details
- Delete therapist records
- Assign therapists to therapy programs
- Track therapist availability

### 🧠 Therapy Program Management
- Create therapy programs
- Update program details
- Delete therapy programs
- Define program duration and cost
- Assign therapists to programs

### 🧑‍🤝‍🧑 Patient Management
- Register new patients
- Update patient information
- Delete patient records
- Store patient medical history
- Track therapy enrollments

### 📅 Therapy Session Scheduling
- Schedule therapy sessions
- Assign therapists to sessions
- Reschedule sessions
- Cancel appointments

### 💳 Payment & Invoice Management
- Record therapy payments
- Generate invoices
- Track completed and pending payments

### 📊 Reporting
- Therapist performance reports
- Therapy session statistics
- Financial reports
- Patient therapy history

---

## 📸 System Screenshots

### 🏠 Home Page
<img width="1300" height="833" alt="Screenshot from 2026-03-11 14-39-57" src="https://github.com/user-attachments/assets/8c35a0e5-7d73-4461-aa39-2d329420ef32" />

### 🔐 Admin Login
<img width="1300" height="833" alt="Screenshot from 2026-03-11 14-40-20" src="https://github.com/user-attachments/assets/a37322ce-4eb6-4673-83f9-4f54c1690cdb" />

### 📊 Admin Dashboard
<img width="1300" height="833" alt="Screenshot from 2026-03-11 14-40-44" src="https://github.com/user-attachments/assets/264c300d-6e96-422e-9772-d92e04ba5e8e" />

### 🧾 Receptionist Dashboard
<img width="1300" height="833" alt="Screenshot from 2026-03-11 14-47-13" src="https://github.com/user-attachments/assets/4e27d8ce-beec-4e22-94a8-3a38fd529cfc" />

### 👥 User Management
<img width="1300" height="833" alt="Screenshot from 2026-03-11 14-41-00" src="https://github.com/user-attachments/assets/b7f65fc3-8f2f-4c6e-8d4f-4155b932dbc2" />

### 👩‍⚕️ Therapist Management
<img width="1300" height="833" alt="Screenshot from 2026-03-11 14-42-28" src="https://github.com/user-attachments/assets/6e85db53-0531-4eae-99dc-cb7f7828230a" />

### 🧑‍🤝‍🧑 Patient Management
<img width="1300" height="833" alt="Screenshot from 2026-03-11 14-42-50" src="https://github.com/user-attachments/assets/8942782a-fdaa-428d-b08a-123246e471f4" />

### 🧠 Therapy Program Management
<img width="1300" height="833" alt="Screenshot from 2026-03-11 14-43-05" src="https://github.com/user-attachments/assets/0c5c38eb-aecf-418f-9b6c-1e5f1b3387c0" />

### 📅 Therapy Session Management
<img width="1300" height="833" alt="Screenshot from 2026-03-11 14-43-15" src="https://github.com/user-attachments/assets/ed9734c4-dfbb-4ab3-a73f-682504205ee6" />

### 📊 Reports
<img width="1300" height="833" alt="Screenshot from 2026-03-11 14-43-41" src="https://github.com/user-attachments/assets/c3f8a54d-cb7f-4b68-ac2c-b67ae81f52e7" />

---

## 🧩 Therapy Programs

| Program ID | Program Name | Duration | Fee |
|------------|-------------|----------|------|
| MT1001 | Cognitive Behavioral Therapy | 12 Weeks | LKR 80,000 |
| MT1002 | Mindfulness-Based Stress Reduction | 8 Weeks | LKR 50,000 |
| MT1003 | Dialectical Behavior Therapy | 16 Weeks | LKR 100,000 |
| MT1004 | Group Therapy Sessions | 6 Months | LKR 120,000 |
| MT1005 | Family Counseling | 3 Months | LKR 40,000 |

---

## 🛠 Technologies Used

### Backend
- Java
- Hibernate ORM
- MySQL
- Maven

### Security
- BCrypt Password Encryption

### Concepts Implemented
- Object Relational Mapping (ORM)
- Hibernate Entity Relationships
- One-to-Many Relationships
- Many-to-One Relationships
- Hibernate Query Language (HQL)

---

## 🗄 Database Entities

The system includes the following main entities:

- User (Admin / Receptionist)
- Therapist
- Therapy Program
- Patient
- Therapy Session
- Payment

### Entity Relationships

- Therapist → Therapy Sessions (**One-to-Many**)
- Patient → Therapy Sessions (**One-to-Many**)
- Therapy Program → Therapists

---

## ⭐ Special Implementations

### HQL Join Query

The system includes an advanced **HQL join query** to retrieve:

Patients who have registered for **all available therapy programs**.

### Password Security

User passwords are encrypted using **BCrypt hashing** to ensure secure authentication.

---

## 📂 Project Structure

```bash
Serenity-Mental-Health-Therapy-Center-System
│
├── .idea
├── .mvn
├── src
│ └── main
│ ├── java
│ │ └── lk.ijse.hibernate.serenitymentalhealththerapycenter
│ │ ├── bo
│ │ │ ├── custom
│ │ │ │ └── impl
│ │ │ ├── BOFactory
│ │ │
│ │ ├── config
│ │ │ └── FactoryConfiguration
│ │ │
│ │ ├── controller
│ │ │
│ │ ├── dao
│ │ │ ├── custom
│ │ │ │ └── impl
│ │ │ ├── CrudDAO
│ │ │ ├── DAOFactory
│ │ │ └── SuperDAO
│ │ │
│ │ ├── dto
│ │ ├── entity
│ │ ├── util
│ │ ├── view.tdm
│ │ │
│ │ └── AppInitializer
│ │
│ └── module-info.java
│
├── resources
│ ├── assets
│ │ ├── images
│ │ └── styles
│ │
│ ├── reports
│ ├── view
│ └── hibernate.properties
│
└── pom.xml
```

---

### 🏗 Architecture

The system follows a **Layered Architecture**:

**Controller Layer**
- Handles UI interactions and user requests.

**Business Logic Layer (BO)**
- Contains the core business logic of the application.

**Data Access Layer (DAO)**
- Handles database operations using Hibernate.

**Entity Layer**
- Represents database tables as Hibernate entities.

**DTO Layer**
- Transfers data between layers.

---

### 📦 Design Patterns Used

- **DAO Pattern**
- **Factory Pattern (DAOFactory & BOFactory)**
- **Layered Architecture**
- **Hibernate ORM Mapping**

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Hansana-Sandamini/Serenity-Mental-Health-Therapy-Center-System.git
```


### 2️⃣ Open the Project

Open the project in **IntelliJ IDEA or Eclipse**.

### 3️⃣ Configure the Database

Create a **MySQL database** and update the configuration in:
```bash
hibernate.cfg.xml
```

### 4️⃣ Build the Project
```bash
mvn clean install
```

### 5️⃣ Run the Application

Run the main class to start the system.


