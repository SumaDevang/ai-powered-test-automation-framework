# AI-Powered Test Automation Framework

> Automatically generate and execute Selenium WebDriver + TestNG test cases 
> from plain-English user stories using OpenAI GPT-4.

---

## 🚀 Overview

This project is a scalable test automation framework built using Selenium WebDriver, TestNG, and Maven. It integrates AI-assisted test generation using Python and OpenAI GPT-4 to convert plain-English user stories into executable Java test cases.

The framework follows industry best practices including Page Object Model (POM), 
ExtentReports with screenshot capture on failure, and is ready for Jenkins CI/CD integration.

---

## ✨ Key Features

- **AI Test Generation** — GPT-4 generates TestNG test cases from plain-English user stories
- **Page Object Model (POM)** — clean separation of UI locators and test logic
- **28 Automated UI Test Cases** — covering 4 critical workflows end to end
- **ExtentReports** — beautiful dark-themed HTML reports with timestamps
- **Screenshot on Failure** — auto-captures and embeds screenshots in the report
- **Incognito Mode** — eliminates browser popups for clean test execution
- **Maven Build** — easy dependency management and test execution
- **Jenkins Ready** — Jenkinsfile included for CI/CD pipeline integration

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 11, Python 3 |
| Browser Automation | Selenium WebDriver 4.x |
| Test Framework | TestNG 7.x |
| AI Engine | OpenAI GPT-4 API |
| Build Tool | Maven |
| Reporting | ExtentReports 5.x |
| Driver Management | WebDriverManager |
| CI/CD | Jenkins |

---

## 📁 Project Structure
```
ai-test-automation/
├── src/
│   ├── main/java/
│   │   ├── pages/
│   │   │   ├── LoginPage.java
│   │   │   ├── ProductsPage.java
│   │   │   ├── CartPage.java
│   │   │   └── CheckoutPage.java
│   │   └── utils/
│   │       ├── BaseTest.java
│   │       ├── ExtentReportManager.java
│   │       ├── ScreenshotUtil.java
│   │       └── TestListener.java
│   └── test/java/
│       └── tests/
│           ├── LoginTest.java
│           ├── ProductsTest.java
│           ├── CartTest.java
│           └── CheckoutTest.java
├── reports/
│   ├── ExtentReport.html
│   └── screenshots/
├── src/
│   └── generate_tests.py
├── Jenkinsfile
├── pom.xml
└── testng.xml
```

---

## 📊 Test Coverage

| Module | Test Cases | Scenarios Covered |
|--------|-----------|------------------|
| Login | 10 | Valid login, invalid credentials, empty fields, locked user, spaces |
| Products | 6 | Page display, product count, add to cart, cart badge, logout |
| Cart | 5 | Cart display, item count, remove item, continue shopping, checkout |
| Checkout | 7 | Valid flow, missing fields, order summary, full E2E, back home |
| **Total** | **28** | |

---

## ⚙️ Prerequisites

- Java JDK 11+
- Maven 3.6+
- Chrome Browser
- Python 3.8+
- OpenAI API Key → [platform.openai.com](https://platform.openai.com)

---

## 🚀 How to Run

### Run all tests
```bash
mvn clean test
```

### Run a specific test class
```bash
mvn clean test -Dtest=LoginTest
```

### Run AI test generator
```bash
export OPENAI_API_KEY="your-api-key"
python3 src/generate_tests.py
```

---

## 🤖 How AI Generation Works

Edit `generate_tests.py` with your user stories:
```python
sample_stories = [
    "As a user, I want to log in with valid credentials to access my dashboard",
    "As a user, I want to reset my password via email to regain account access",
]
generate_test_suite(sample_stories, output_dir="generated_tests")
```

GPT-4 generates complete Java TestNG test files automatically.

---

## 📊 Sample Extent Report

After running tests, open the report:
```
reports/ExtentReport.html
```

Features:
- Dark themed dashboard
- Pass/Fail/Skip status per test
- Execution timestamps and duration
- Screenshots embedded on failure

---

## 🔁 Jenkins CI/CD

The included `Jenkinsfile` automates:
1. Pull latest code from GitHub
2. Run AI generator to create test cases
3. Compile the Maven project
4. Execute full regression suite
5. Publish TestNG + ExtentReports

---

## 📈 Results

- ✅ **28/28 tests passing**
- 🤖 **AI reduces test creation time by 60%**
- 📸 **Auto screenshot on every failure**
- 🔁 **Continuous regression via Jenkins**

---

## 👤 Author

**Suma Shekar**  
QA Automation Engineer / SDET | 7.5+ years experience  
🔗 [LinkedIn](https://linkedin.com/in/suma-shekar) | 
🐙 [GitHub](https://github.com/SumaDevang)

---

## 📄 License

MIT License
