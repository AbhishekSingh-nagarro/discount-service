# Retail Discount Calculator

## Description
A Spring Boot application to calculate net payable amount after applying various discounts.

## Requirements
- Java 8+
- Maven

## Build and Run
```sh
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Run unit tests
mvn test

# Generate test coverage report
mvn jacoco:report

# After running above command open target/site/jacoco/index.html in a browser to view the code coverage report.

# run SonarQube Analysis
mvn sonar:sonar

# check the running status of application hit the below cURL:
curl --location 'http://localhost:8080/actuator/health'

#check the application endpoint hit the below cURL:
curl --location 'http://localhost:8080/api/discount/calculate' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic QWJoaXNoZWs6cGFzc3dvcmQ=' \
--data '{
    "user": {
        "isEmployee": true,
        "isAffiliate": false,
        "isLongTermCustomer": false
    },
    "items": [
        {
            "name": "item1",
            "price": 200,
            "isGrocery": false
        },
        {
            "name": "item2",
            "price": 300,
            "isGrocery": false
        }
    ]
}'

# UML diagram