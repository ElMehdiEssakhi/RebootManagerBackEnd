# RebootManager Backend

A **Spring Boot backend for a data engineering and monitoring pipeline** designed to process and analyze machine reboot logs.

The application ingests reboot logs, cleans and parses the raw data, extracts the information required for analysis, and stores the processed data for visualization through a monitoring dashboard. It also includes an alerting mechanism that sends email notifications when a required machine reboot has not been performed.

## Features

- REST API built with Spring Boot
- Reboot log ingestion and processing
- Data cleaning and parsing
- Extraction of relevant reboot information
- Processed data storage using MySQL
- Dashboard-ready data for monitoring and analysis
- Automated email alerts when expected reboots are not detected
- Role-based access control (Manager / Technician)
- Scheduled tasks for automated processing and monitoring
- Docker-ready

## Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Docker
- IntelliJ IDEA

## Data Pipeline

The backend is organized around a processing pipeline:

```text
Raw Reboot Logs
      │
      ▼
Log Ingestion
      │
      ▼
Data Cleaning
      │
      ▼
Log Parsing & Information Extraction
      │
      ▼
Processed Data
      │
      ├──────────────► Monitoring Dashboard
      │
      ▼
Reboot Analysis
      │
      ▼
Alert Generation
      │
      ▼
Email Notification
```

The pipeline processes raw reboot logs to identify relevant information such as machines, reboot events, timestamps, and reboot status. The cleaned and structured data is then made available for monitoring and analysis.

When the system detects that an expected reboot has not been performed, an automated email alert can be generated and sent to the appropriate recipients.

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/ElMehdiEssakhi/RebootManagerBackEnd.git

cd GestionRebootBackEnd
```

### 2. Configure the Application

Configure the required database and application settings in:

```text
src/main/resources/application.properties
```

Sensitive configuration such as database credentials and email credentials should be provided through environment variables rather than committed to the repository.

### 3. Build the Application

Using Maven:

```bash
mvn clean install
```

### 4. Run the Application

```bash
java -jar target/gesReboot-1.0.0-SNAPSHOT.jar
```

Alternatively, the application can be started directly from your IDE or using Maven:

```bash
mvn spring-boot:run
```

## Project Purpose

The project aims to automate the monitoring of machine reboot operations by transforming raw reboot logs into structured and actionable information.

Instead of manually analyzing logs, the pipeline handles **data ingestion, cleaning, parsing, processing, monitoring, and alerting**, providing the necessary data for a centralized dashboard and notifying users when expected reboot operations are missing.
