## Write Code, Get Money, Repeat

Tracking income across streams can be a chore. That's why I built the Half a Milli Dashboard.

## About The Half a Milli Dashboard

The Half a Milli Dashboard tracks how close I am to making $500k per year across all income streams in a unified interface.

It's more than a paycheck tracker; it's about intention and action.

## Features:

- `Effortless Income Tracking:` Ditch spreadsheets! Track income with ease.

- `Real-Time Progress:` Stay motivated with progress & charts to $500k. Celebrate milestones.

- `Data-Driven Decisions:` Analyze trends to find your top earners. Optimize & maximize earnings.

- `Focus on Your Craft:` Half a Milli Dashboard frees you to focus on coding, creating, and growing.

## Tech Stack:

- `Admin Dashboard:` Built with Bootstrap & JavaScript for a smooth experience.

- `API:` Powerful Spring Framework & Java for secure data handling.

- `In-Memory Storage:` For quick and efficient access to your data without a persistent database.

- `Hosting:` Blazing-fast Linux server on DigitalOcean cloud.

## Supercharge Your Hustle:

- Define Income Sources: Freelancing gigs, coding contests, even grandma's birthday money – track it all!

- Organize Your Income: Create custom categories to understand where your money comes from.

- Full Control of your data: Add, edit, or delete income sources and categories with ease.

## How to Run the Code

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven
- Git

### Steps to Run

1. **Clone the Repository:**

   ```bash
   git clone [[https://github.com/Supernorwood/five](https://github.com/Supernorwood/five)]
   cd [five]
   ```

2. **Build the Project:**

   ```bash
   mvn clean install
   ```

3. **Run the Application:**

   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application:**
   Open your web browser and go to `http://localhost:8080`.

### Example Usage

1. **Create an Income Stream:**

   ```bash
   curl -X POST "http://localhost:8080/income-streams" -H "Content-Type: application/json" -d '{"estimatedEarningsPerYear":30000.00, "source":"New Source", "name":"New Name", "description":"New Description"}'
   ```

2. **Get All Income Streams:**

   ```bash
   curl -X GET "http://localhost:8080/income-streams"
   ```

3. **Get Income Streams by Minimum Earnings:**

   ```bash
   curl -X GET "http://localhost:8080/income-streams/filter?minEarnings=20000"
   ```

4. **Get Income Streams by Source:**

   ```bash
   curl -X GET "http://localhost:8080/income-streams/source?source=E-commerce Store"
   ```

5. **Get Total Estimated Earnings:**

   ```bash
   curl -X GET "http://localhost:8080/income-streams/total-earnings"
   ```

6. **Get Percentage Towards Goal:**

   ```bash
   curl -X GET "http://localhost:8080/income-streams/goal-percentage?goal=500000"
   ```

7. **Get Average Estimated Earnings:**

   ```bash
   curl -X GET "http://localhost:8080/income-streams/average-earnings"
   ```

8. **Get Income Stream Count:**

   ```bash
   curl -X GET "http://localhost:8080/income-streams/count"
   ```

9. **Update an Income Stream:**

```bash
curl -X PUT "http://localhost:8080/income-streams/1" -H "Content-Type: application/json" -d '{"estimatedEarningsPerYear":25000.00, "source":"Updated Source", "name":"Updated Name", "description":"Updated Description"}'
```

10. **Delete an Income Stream:**

```bash
curl -X DELETE "http://localhost:8080/income-streams/1"
```

11. **Create a Budget:**

```bash
curl -X POST "http://localhost:8080/budgets" -H "Content-Type: application/json" -d '{"allocation":5000.00, "category":"Marketing", "description":"Budget for marketing campaigns"}'
```

12. **Get All Budgets:**

```bash
curl -X GET "http://localhost:8080/budgets"
```

13. **Update a Budget:**

```bash
curl -X PUT "http://localhost:8080/budgets/1" -H "Content-Type: application/json" -d '{"allocation":15000.00, "category":"Operations", "description":"Updated budget for operational expenses"}'
```

14. **Delete a Budget:**

```bash
curl -X DELETE "http://localhost:8080/budgets/1"
```

15. **Get Income Streams by Budget:**

```bash
curl -X GET "http://localhost:8080/budgets/1/income-streams"
```

16. **Get Expense Streams by Budget:**

```bash
curl -X GET "http://localhost:8080/budgets/1/expense-streams"
```

17. **Get Total Allocation by Budget:**

```bash
curl -X GET "http://localhost:8080/budgets/1/total-allocation"
```

## Follow the Execution:

- GitHub: [[Supernorwood](https://github.com/Supernorwood/five)]
