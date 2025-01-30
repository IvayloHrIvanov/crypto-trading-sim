# Crypto Trading Simulator

Instructions on how to run the application (with IntelliJ, Visual Studio Code and PgAdmin4):

1. Install all of the software stated in the requirments.txt:
  - Install Node.js and npm: [Download here](https://nodejs.org/en)
  - Install and Setup Java 11: [Download here](https://jdk.java.net/java-se-ri/11-MR3) [Setup here](https://stackoverflow.com/questions/52511778/how-to-install-openjdk-11-on-windows)
  - Install PostgreSQL: [Download Here](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
  - Install PgAdmin4: [Download Here](https://www.pgadmin.org/download/pgadmin-4-windows/)
  - Install IntelliJ: [Download Here](https://www.jetbrains.com/idea/download/?section=windows)
  - Install Visual Studio Code: [Download Here](https://code.visualstudio.com/)
  - Git: [Download Here](https://git-scm.com/downloads)
  - Install Lombok or enable it in IntelliJ: [Download Here](https://projectlombok.org/)

2. Clone this repository [Tutorial Here](https://blog.hubspot.com/website/clone-github-repository)
3. Open PgAdmin4 and create new Database. Right-click on the new DB, select Restore, choose one of the backups in Database and Save
4. Open Backend/crypto.tradingplatform in IntelliJ
  - Install and Enable Lombok from IntelliJ plugins
  - Modify application.properties to match your PostgreSQL configuration (port, database name, username, password). Example:

    #Database
    spring.datasource.url=jdbc:postgresql://localhost:5432/crypto_trading
    spring.datasource.username=postgres
    spring.datasource.password=0000

    #JPA
    spring.jpa.hibernate.ddl-auto=none
    spring.jpa.show-sql=true

    #Kraken WebSocket API (for reference)
    kraken.websocket.url=wss://ws.kraken.com/v2


  - Run the project from Backend/crypto.tradingplatform/src/main/java/com/example/crypto/tradingplatform/CryptoTradingPlatformApplication.java
5. Open Frontend/react-crypto-app in Visual Studio Code
  - Open New Terminal and run: npm install
  - After the module installation run: npm run dev
6. Follow the http://localhost connection to open the App


### This repository is part of a hiring process. The goal of the project can be found below

Goal:

Develop a web application that simulates a cryptocurrency trading platform, allowing users to:

* View real-time prices of the top 20 cryptocurrencies using the Kraken API.
* Maintain a virtual account balance for buying and selling crypto with a history of all transactions made (without actually buying or selling at the exchange).
* Reset their account balance to a starting value.

Technical Requirements:

* Frontend: HTML, CSS, JavaScript (consider using a framework like React or Vue.js for better organization and maintainability).
* Backend: Java with Spring Boot.
* API Integration: Utilize the Kraken WebSocket API (https://docs.kraken.com/api/docs/websocket-v2/ticker) to fetch real-time cryptocurrency prices.
* Data Storage: In-memory storage is sufficient for this simulation. Persistent storage for bonus points.

Functionality:

1. Display Top 20 Crypto Prices:
  * Dynamically update the displayed prices in real-time as they change on the exchange.
  * Clearly present the cryptocurrency name, symbol, and current price in a user-friendly table or list format.
2. Account Balance and Transactions:
  * Initialize a virtual account balance with a starting value (e.g., $10,000).
  * Implement a mechanism for users to buy and sell cryptocurrencies:
    * Buying:
      * Allow users to specify the amount of a cryptocurrency they want to purchase.
      * Deduct the purchase cost (quantity * price) from the account balance.
      * Display a confirmation message or update the UI to reflect the updated balance and holdings.
    * Selling:
      * Allow users to specify the amount of a cryptocurrency they want to sell.
      * Increase the account balance by the selling amount (quantity * price).
      * Update the UI to reflect the updated balance and holdings.
  * Ensure that transactions respect account balance limitations (users cannot buy more crypto than their current balance allows).
  * Provide clear error messages if invalid input is entered (e.g., negative purchase quantity).
3. Transactions history:
  * A user should be able to see a history log of all his transactions (including whether they made profit or loss)
4. Reset Button:
  * Include a button or link that resets the account balance to the initial value.
  * Upon clicking the reset button, update the UI to show the original balance and clear any cryptocurrency holdings.

Additional Considerations:

* Functionality: Successful implementation of all required features (price display, buying/selling, reset).
* Code Quality: Well-structured, readable, and maintainable code following best practices.
* Error Handling: Robust error handling to gracefully handle invalid user input, API errors, or unexpected situations.
* Performance: For real-time updates, consider techniques like efficient data structures and caching to optimise performance.
* Security: Since this is a simulated application, security is not a major concern.
* Scalability (bonus objective): An explanation of how you would scale your application to multiple instances will get you bonus points.
* Documentation (bonus objective): Include clear documentation alongside the codebase to explain the purpose of different APIs and design decisions.
* Testing (bonus objective): Unit tests to ensure the correctness of individual components and integration tests to verify overall application functionality.
