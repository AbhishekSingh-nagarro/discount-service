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

# Run unit tests
mvn test

# Generate test coverage report
mvn jacoco:report

# After running above command open target/site/jacoco/index.html in a browser to view the code coverage report.

# run SonarQube Analysis
mvn sonar:sonar

# Run the application
mvn spring-boot:run

#the application will start on below url:
http://localhost:8080 

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

# Parameters in above request:
    - isEmployee (boolean): If the user is an employee of the store.
    - isAffiliate (boolean): If the user is an affiliate of the store.
    - isLongTermCustomer (boolean): If the user has been a customer for over 2 years.
    - items (List<String>): List of items in the bill.
        - name  : item name
        - price : item price
        - isGrocery : if the item is of type grocery or not.

# UML diagram
![discount_service_uml](https://github.com/user-attachments/assets/a29cf2e1-508e-4f58-8973-110d21f2bc1a)

#Implementation :
    - DiscountserviceApplication.java   -> Entry point of spring boot application
    - User.java                         -> Store the information about the user.
    - Bill.java                         -> Represents the bill with the total amount and items.
    - Item.java                         -> Store the information about the item details e.g. is name, price etc.
    - DiscountService.java              -> service class to implement the core logic, After validation it is using strategy 
                                           and factory pattern which is used to find the right strategy for the requested 
                                           user and factory is used to decide the type of discount needs to apply on the same.
                                           Using the above pattern so that extension and modification will be easy in future. 
    - DiscountStrategy.java             -> interface used to decide the strategy 
    - GlobalExceptionHandler.java       -> This class has been used to manage the exception and other related
                                           specific exception class is also added seperately. 
    - EmployeeDiscount.java             -> This class implements the DiscountStrategy interface to provide discount on employee type 
                                           i.e. 30% discount
    - LongTermCustomerDiscount.java     -> This class implements the DiscountStrategy interface to provide discount on customer who
                                           has been related to store for more than 2 year e.g. 5% discount.
    - AffiliateDiscount.java            -> This class implements the DiscountStrategy interface to provide discount on affiliate type 
                                           i.e. 10% discount 
    - NoDiscount.java                   -> This class implements the logic for customer who is not eligible for any discount
    - Constant.java                     -> Handling general message used in application.
                                         
                                            
                                          


