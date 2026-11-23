# Selenium Automation Framework

## Project Overview

This project is a Selenium WebDriver automation framework developed using Java, TestNG and Maven. It is designed to automate web application test scenarios using a structured and reusable framework approach.

The framework follows the Page Object Model (POM) design pattern to improve code reusability, maintainability and readability.

## Technologies Used

| Technology | Purpose |
|---|---|
| Java 26| Programming language |
| Selenium WebDriver 4.48.0 | Web UI automation |
| TestNG 7.12.0 | Test execution and test management |
| Maven | Build and dependency management |
| Apache POI 5.5.1 | Excel test data handling |
| Extent Reports 5.1.2 | Test execution reporting |
| Log4j 2.26.1 | Logging |

## Framework Features

- Page Object Model (POM)
- Reusable base class for test setup and teardown
- Cross-browser execution support (if configured)
- Data-driven testing using Excel
- TestNG annotations and assertions
- Extent Reports integration
- Log4j-based logging
- Maven-based test execution

## Project Structure

```text
SeleniumWebDriver
│
├── src
│   ├── main
│   │   └── resources
│   │
│   └── test
│       ├── java
│       │   └── com.orangehrm
│       │       ├── base
│       │       ├── pages
│       │       ├── tests
│       │       └── utilities
│       │
│       └── resources
│           ├── config.properties
│           └── TestData.xlsx
│
├── .gitignore
├── pom.xml
└── README.md
```

*Note: Adjust the folder names above to match the actual project structure.*

## Prerequisites

Before running the automation tests, ensure you have:

- Java JDK 26 or later
- Maven installed and configured
- Chrome or Firefox browser
- An IDE such as Eclipse or IntelliJ IDEA
- Git (optional, for cloning the repository)

## Setup Instructions

1. Clone the repository:

   ```bash
   git clone -b Selenium https://github.com/sugalya14-Automation/Automation.git
   ```

2. Navigate to the project directory:

   ```bash
   cd Automation
   ```

3. Import the project into your IDE as an existing Maven project.

4. Allow Maven to download the required dependencies.

## Running Tests

To clean the previous build and execute tests:

```bash
mvn clean test
```

To execute tests using a specific TestNG suite file:

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

*The second command requires the Maven Surefire Plugin to be configured to accept the `suiteXmlFile` property.*

## Test Data

The framework uses Excel files for test data management through Apache POI.

Update the relevant test data in the Excel file before execution, where applicable.

## Reports and Logs

- Extent Reports: Used to generate execution reports.
- Log4j: Used to capture execution logs.
- TestNG: Provides test execution results.

Generated reports and logs are excluded from Git using `.gitignore`.

## Author

Sugalya

