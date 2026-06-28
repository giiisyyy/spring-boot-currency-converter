# 💱 Global Currency Converter 💱

An enterprise-grade web application built with Java and Spring Boot designed to streamline real-time currency conversion, track global exchange rates dynamically, and provide instant multi-language localization for seamless global use.

## 🚀 Key Features

* **Spring MVC i18n Architecture:** Implements standard Spring internationalization utilizing localized message source bundles (`messages_*.properties`) for real-time dynamic language resolution.
* **Real-Time Exchange Analytics:** Background evaluation logic leveraging robust external API integration to instantly fetch, sort, and display current global currency valuations.
* **Secure Model Injection:** Enforces strict model attributes binding through Thymeleaf views templates, separating controller routing contexts from the user presentation layer.
* **Isolated Mockito Test Suite:** Fully decoupled testing environment leveraging `@MockBean` slices to intercept external HTTP traffic, ensuring reliable build validations independent of internet connectivity.

## 🛠️ Tech Stack

* **Backend Framework:** Spring Boot 3.2.x / 3.3.x
* **Template Engine:** Thymeleaf
* **Language:** Java 17 / 21
* **Build Tool:** Maven 3.6+

## 📦 Installation & Setup

1. Clone the repository:

```bash
git clone https://github.com/giiisyyy/spring-boot-currency-converter.git
```

```bash
cd spring-boot-currency-converter
```

2. Configure application environment (Optional):
Verify your live exchange data endpoint properties inside src/main/resources/application.properties.

3. Build and compile the project infrastructure:

```bash
mvn clean install
```

4. Run the Spring Boot application locally:

```bash
mvn spring-boot:run
```

5. Access the user portal from your browser:
Open http://localhost:8080 to explore the system.

🧪 Automated Test Suite
To execute the unit, integration, and mocked controller verification tests safely without hitting external endpoints, trigger the Surefire Maven lifecycle phase:

```bash
mvn test
```

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
