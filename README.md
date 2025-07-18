# 🏦 LedgerBridge

> **A Digital Invoice Discounting Platform for MSMEs**

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-19.1.0-blue.svg)](https://reactjs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.8.3-blue.svg)](https://www.typescriptlang.org/)
[![Tailwind CSS](https://img.shields.io/badge/Tailwind%20CSS-4.1.5-38B2AC.svg)](https://tailwindcss.com/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-orange.svg)](https://www.mysql.com/)

LedgerBridge is a comprehensive digital invoice discounting platform inspired by TReDS (Trade Receivables Discounting System). It empowers Micro, Small, and Medium Enterprises (MSMEs) to optimize their cash flow by facilitating seamless interactions between suppliers, buyers, and financiers in the receivables financing ecosystem.

## 📋 Table of Contents

- [🚀 Features](#-features)
- [🏗️ Architecture](#️-architecture)
- [🛠️ Tech Stack](#️-tech-stack)
- [📦 Prerequisites](#-prerequisites)
- [⚙️ Installation](#️-installation)
- [🚀 Running the Application](#-running-the-application)
- [📡 API Documentation](#-api-documentation)
- [🖥️ User Roles & Workflows](#️-user-roles--workflows)
- [📱 Screenshots](#-screenshots)
- [🤝 Contributing](#-contributing)
- [👥 Team](#-team)

## 🚀 Features

### 🏢 For Suppliers (MSMEs)

- **Invoice Upload**: Seamlessly upload invoices with buyer details
- **Real-time Tracking**: Monitor invoice status from upload to payment
- **Bidding Dashboard**: View and compare financing offers from multiple financiers
- **Cash Flow Management**: Get instant access to working capital
- **Document Management**: Secure storage and retrieval of financial documents

### 🛒 For Buyers

- **Invoice Approval Workflow**: Review and approve supplier invoices
- **Payment Scheduling**: Manage payment timelines and due dates
- **Supplier Management**: Maintain supplier relationships and credit profiles
- **Audit Trail**: Complete transaction history and compliance tracking

### 💰 For Financiers

- **Investment Opportunities**: Browse and bid on approved invoices
- **Risk Assessment**: Advanced analytics for credit risk evaluation
- **Portfolio Management**: Track investments and returns
- **Automated Bidding**: Set parameters for automatic bid placement
- **Due Payment Tracking**: Monitor and manage payment collections

### 🔧 System Features

- **Multi-role Authentication**: Secure JWT-based authentication system
- **Real-time Notifications**: SMS and email notifications via Twilio integration
- **OTP Verification**: Two-factor authentication for secure transactions
- **Responsive Design**: Mobile-first responsive UI with Tailwind CSS
- **Data Analytics**: Comprehensive reporting and analytics dashboard
- **Secure Transactions**: End-to-end encryption and secure payment processing

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │    Backend      │    │    Database     │
│   (React +      │◄──►│  (Spring Boot)  │◄──►│    (MySQL)      │
│   TypeScript)   │    │                 │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│  UI Components  │    │  REST APIs      │    │   Data Models   │
│  - Dashboard    │    │  - Auth         │    │  - Users        │
│  - Forms        │    │  - Invoice      │    │  - Invoices     │
│  - Charts       │    │  - Bidding      │    │  - Bids         │
│  - Tables       │    │  - Notifications│    │  - Payments     │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🛠️ Tech Stack

### Frontend

- **Framework**: React 19.1.0 (with TypeScript)
- **Styling**: Tailwind CSS 4.1.5
- **UI Components**:
  - Material-UI (MUI) 7.1.0
  - Heroicons
  - Lucide React
  - Radix UI
- **State Management**: React Context API
- **Routing**: React Router DOM 7.5.3
- **Charts**: Chart.js, Recharts
- **HTTP Client**: Axios 1.9.0
- **Build Tool**: Vite 6.3.5

### Backend

- **Framework**: Spring Boot 3.4.5
- **Language**: Java 24
- **Security**: Spring Security with JWT
- **Database**: MySQL 8.0
- **ORM**: Spring Data JPA
- **Communication**: Twilio (SMS/Voice)
- **Build Tool**: Maven
- **Development**: Spring Boot DevTools (Hot Reload)

### Development Tools

- **Code Quality**: ESLint
- **Type Checking**: TypeScript ~5.8.3
- **Package Management**: npm (Frontend), Maven (Backend)
- **Version Control**: Git

## 📦 Prerequisites

Before running this application, make sure you have the following installed:

- **Node.js** (v18 or higher)
- **npm** or **yarn**
- **Java** (JDK 24 or compatible version)
- **Maven** (3.6 or higher)
- **MySQL** (8.0 or higher)
- **Git**

## ⚙️ Installation

### 1. Clone the Repository

```bash
git clone https://github.com/sahil16-12/ledgerbridge.git
cd ledgerbridge
```

### 2. Database Setup

```sql
-- Create database
CREATE DATABASE ledgerbridge;
```

### 3. Backend Configuration

Navigate to the backend directory and configure your `application.properties`:

```bash
cd backend
```

Create/update `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/ledgerbridge
spring.datasource.username=ledgerbridge_user
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Server Configuration
server.port=8080

# JWT Configuration
jwt.secret=your-secret-key
jwt.expiration=86400000

# Twilio Configuration
twilio.account.sid=your-twilio-sid
twilio.auth.token=your-twilio-token
twilio.phone.number=your-twilio-phone

# CORS Configuration
cors.allowed.origins=http://localhost:5173
```

### 4. Frontend Configuration

Navigate to the frontend directory:

```bash
cd ../frontend
```

Create a `.env` file:

```env
VITE_API_BASE_URL=http://localhost:8080/api
VITE_APP_NAME=LedgerBridge
```

### 5. Install Dependencies

**Backend:**

```bash
cd backend
./mvnw clean install
```

**Frontend:**

```bash
cd frontend
npm install
```

## 🚀 Running the Application

### Method 1: Development Mode

**Start Backend:**

```bash
cd backend
./mvnw spring-boot:run
```

**Start Frontend:**

```bash
cd frontend
npm run dev
```

### Method 2: Production Build

**Backend:**

```bash
cd backend
./mvnw clean package
java -jar target/Backend-0.0.1-SNAPSHOT.jar
```

**Frontend:**

```bash
cd frontend
npm run build
npm run preview
```

The application will be available at:

- **Frontend**: http://localhost:5173
- **Backend API**: http://localhost:8080

## 📡 API Documentation

### Authentication Endpoints

| Method | Endpoint                       | Description            |
| ------ | ------------------------------ | ---------------------- |
| POST   | `/api/auth/register/supplier`  | Register new supplier  |
| POST   | `/api/auth/register/buyer`     | Register new buyer     |
| POST   | `/api/auth/register/financier` | Register new financier |
| POST   | `/api/auth/login`              | User login             |
| POST   | `/api/auth/logout`             | User logout            |
| POST   | `/api/otp/send`                | Send OTP verification  |
| POST   | `/api/otp/verify`              | Verify OTP             |

### Invoice Management

| Method | Endpoint                     | Description             |
| ------ | ---------------------------- | ----------------------- |
| GET    | `/api/invoices`              | Get all invoices        |
| POST   | `/api/invoices`              | Upload new invoice      |
| GET    | `/api/invoices/{id}`         | Get invoice by ID       |
| PUT    | `/api/invoices/{id}`         | Update invoice          |
| DELETE | `/api/invoices/{id}`         | Delete invoice          |
| POST   | `/api/invoices/{id}/approve` | Approve invoice (Buyer) |

### Bidding System

| Method | Endpoint                        | Description           |
| ------ | ------------------------------- | --------------------- |
| GET    | `/api/bids`                     | Get all bids          |
| POST   | `/api/bids`                     | Place new bid         |
| GET    | `/api/bids/invoice/{invoiceId}` | Get bids for invoice  |
| PUT    | `/api/bids/{id}/accept`         | Accept bid (Supplier) |
| DELETE | `/api/bids/{id}`                | Cancel bid            |

### Payment Management

| Method | Endpoint                 | Description      |
| ------ | ------------------------ | ---------------- |
| GET    | `/api/payments`          | Get all payments |
| GET    | `/api/payments/due`      | Get due payments |
| POST   | `/api/payments/{id}/pay` | Process payment  |
| GET    | `/api/payments/history`  | Payment history  |

## 🖥️ User Roles & Workflows

### 🏭 Supplier Workflow

```
1. Register & Verify Account
2. Upload Invoice with Buyer Details
3. Wait for Buyer Approval
4. Review Financing Bids
5. Accept Best Bid
6. Receive Immediate Payment
7. Track Transaction Status
```

### 🛒 Buyer Workflow

```
1. Register & Verify Account
2. Review Pending Invoices
3. Approve/Reject Invoices
4. Set Payment Terms
5. Monitor Due Payments
6. Make Payments to Financiers
```

### 💼 Financier Workflow

```
1. Register & Verify Account
2. Browse Approved Invoices
3. Analyze Risk & Credit Score
4. Place Competitive Bids
5. Win Bids & Provide Financing
6. Collect Payments from Buyers
7. Track ROI & Portfolio Performance
```

## 📱 Screenshots

### Landing Page

```
[Dashboard Preview - Modern UI with hero section, features, and call-to-action]
```

### Supplier Dashboard

```
[Invoice management, bidding status, cash flow analytics]
```

### Buyer Dashboard

```
[Pending approvals, payment schedules, supplier management]
```

### Financier Dashboard

```
[Investment opportunities, portfolio tracking, bid management]
```

## 📊 Key Metrics & Analytics

The platform provides comprehensive analytics including:

- **Transaction Volume**: Total value of invoices processed
- **Bid Success Rate**: Percentage of successful bid conversions
- **Average Discount Rate**: Market rate analysis
- **Cash Flow Impact**: Time-to-payment improvements
- **User Engagement**: Platform adoption metrics
- **Risk Assessment**: Default rates and credit analysis

## 🔒 Security Features

- **JWT Authentication**: Secure token-based authentication
- **Role-based Access Control**: Granular permissions system
- **Data Encryption**: End-to-end encryption for sensitive data
- **OTP Verification**: Two-factor authentication
- **API Rate Limiting**: Protection against abuse
- **SQL Injection Prevention**: Parameterized queries
- **CORS Configuration**: Cross-origin resource sharing control

## 🚀 Future Enhancements

- [ ] **Blockchain Integration**: Immutable transaction records
- [ ] **AI-powered Risk Assessment**: Machine learning for credit scoring
- [ ] **Mobile Applications**: Native iOS and Android apps
- [ ] **Advanced Analytics**: Predictive analytics and forecasting
- [ ] **Multi-currency Support**: International transactions
- [ ] **API Gateway**: Microservices architecture
- [ ] **Real-time Chat**: In-app communication system
- [ ] **Document OCR**: Automated invoice data extraction

## 🤝 Contributing

We welcome contributions to LedgerBridge! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch**: `git checkout -b feature/AmazingFeature`
3. **Commit your changes**: `git commit -m 'Add some AmazingFeature'`
4. **Push to branch**: `git push origin feature/AmazingFeature`
5. **Open a Pull Request**

### Development Guidelines

- Follow existing code style and conventions
- Write comprehensive tests for new features
- Update documentation for API changes
- Ensure all tests pass before submitting PR
- Use meaningful commit messages

## 👥 Team

**Project Lead & Full Stack Developer**

- **Name**: Shahil Vora
- **Email**: sahil16december@gmail.com
- **GitHub**: [@sahil16-12](https://github.com/sahil16-12)

## 🙏 Acknowledgments

- **TReDS Platform**: Inspiration for the invoice discounting model
- **Spring Boot Community**: Excellent documentation and support
- **React Community**: Amazing ecosystem and tools
- **Tailwind CSS**: Beautiful and functional design system
- **Open Source Contributors**: All the amazing libraries and tools

## 🌟 Show Your Support

If you find this project helpful, please give it a ⭐️ on GitHub!

---

**Made with ❤️ for the MSME community**

_Empowering small businesses through innovative financial technology_
