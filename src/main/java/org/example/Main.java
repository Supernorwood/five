package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Main implements CommandLineRunner {

    public static final double GOAL_NUMBER = 500_000.00;

    @Autowired
    private IncomeStreamService service;

    @Autowired
    private ExpenseStreamService expenseStreamService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BudgetController budgetService;

    @Override
    public void run(String... args) {
        System.out.println("Starting the Half a Milli Dashboard...\n");

        IncomeStream stream1 = new IncomeStream(10000.00, "Natgo", "Job", "java job");
        System.out.println("Created Income Stream: " + stream1 + "\n");

        IncomeStream stream2 = new IncomeStream(20000.00, "SN", "Job", "TSE");
        System.out.println("Created Income Stream: " + stream2 + "\n");

        ArrayList<IncomeStream> incomeStreams = new ArrayList<>();

        IncomeStream newIncomeStream = new IncomeStream(
                45000.0, // Estimated earnings
                "E-commerce Store", // Source
                "Online retail business", // Name
                "Selling handmade crafts and accessories");

        System.out.println("Created Income Stream: " + newIncomeStream + "\n");
        System.out.println("Initial list of income streams: " + incomeStreams + "\n");

        incomeStreams.add(stream1);
        incomeStreams.add(stream2);
        incomeStreams.add(newIncomeStream);

        System.out.println("Updated list of income streams: " + incomeStreams + "\n");

        // Calculate total estimated earnings
        double totalEarnings = 0.0;
        for (IncomeStream incomeStream : incomeStreams) {
            totalEarnings += incomeStream.getEstimatedEarningsPerYear();
        }

        System.out.println("Total Estimated Earnings Per Year: $" + totalEarnings + "\n");
        System.out.println("Current earnings: $" + totalEarnings + "\n");
        System.out.println("Goal earnings: $" + GOAL_NUMBER + "\n");

        double percentage = (totalEarnings / GOAL_NUMBER) * 100.0;
        if (GOAL_NUMBER == 0) {
            percentage = 0.0; // Avoid division by zero
        }
        System.out.println("You are " + percentage + "% towards your goal.\n");

        double distance = Math.max(0.0, GOAL_NUMBER - totalEarnings);
        System.out.println("You are still $" + distance + " away from your goal.\n");

        IncomeStream createdStream = service
                .createIncomeStream(new IncomeStream(30000.00, "New Source", "New Name", "New Description"));
        System.out.println("Created new Income Stream: \n" + createdStream + "\n");

        double minEarnings = 30000.0;
        List<IncomeStream> filteredStreams = service.getIncomeStreamsByMinEarnings(minEarnings);
        System.out.println("Income Streams with minimum earnings of $" + minEarnings + ": \n" + filteredStreams + "\n");

        String source = "SN";
        System.out.println(
                "Income Streams by Source (" + source + "): " + service.getIncomeStreamsBySource(source) + "\n");

        System.out.println("Total Estimated Earnings: $" + service.getTotalEstimatedEarnings() + "\n");

        System.out.println("Percentage Towards Goal: " + service.getPercentageTowardsGoal(GOAL_NUMBER) + "%\n");

        System.out.println("Average Estimated Earnings: $" + service.getAverageEstimatedEarnings() + "\n");

        System.out.println("Total Income Stream Count: " + service.getIncomeStreamCount() + "\n");

        service.updateIncomeStream(1L,
                new IncomeStream(25000.00, "Updated Source", "Updated Name", "Updated Description"));
        System.out.println("Updated Income Stream with ID 1\n");

        Category category1 = new Category("Freelance", "Income from freelance work");
        Category category2 = new Category("Investments", "Income from investments");

        categoryService.createCategory(category1);
        categoryService.createCategory(category2);

        List<Category> categories = categoryService.getAllCategories();
        System.out.println("All Categories: \n" + categories + "\n");

        createdStream.setCategoryId(category1.getId());
        service.updateIncomeStream(createdStream.getId(), createdStream);
        System.out.println("Assigned category to created stream: \n" + createdStream + "\n");

        List<IncomeStream> incomeStreamList = service.getAllIncomeStreams();
        System.out.println("All Income Streams with Categories: \n" + incomeStreamList + "\n");

        Category updatedCategory = new Category("Updated Freelance", "Updated income from freelance work");
        categoryService.updateCategory(category1.getId(), updatedCategory);
        System.out.println("Updated category: \n" + updatedCategory + "\n");

        categories = categoryService.getAllCategories();
        System.out.println("All Categories after update: \n" + categories + "\n");

        // Assign category to income stream
        service.assignCategoryToIncomeStream(createdStream.getId(), category1.getId());
        System.out.println("Assigned Category to Income Stream: \n" + createdStream + "\n");

        List<IncomeStream> newIncomeStreamList = service.getAllIncomeStreams();
        System.out.println("Updated list of Income Streams after assigning category: \n" + newIncomeStreamList + "\n");

        // Fetch income streams by category
        List<IncomeStream> incomeStreamsByCategory = service.getIncomeStreamsByCategoryId(category1.getId());
        System.out.println("Income Streams in category 'Freelance': \n" + incomeStreamsByCategory + "\n");

        categoryService.deleteCategory(category2.getId());
        categories = categoryService.getAllCategories();
        System.out.println("All Categories after deletion: \n" + categories + "\n");

        // budgets
        Budget budget1 = new Budget(5000.00, "Marketing", "Budget for marketing campaigns");
        Budget budget2 = new Budget(10000.00, "Operations", "Budget for operational expenses");

        budgetService.createBudget(budget1);
        budgetService.createBudget(budget2);

        List<Budget> budgets = budgetService.getAllBudgets();
        System.out.println("All Budgets: \n" + budgets + "\n");

        Budget updatedBudget = new Budget(15000.00, "Operations", "Updated budget for operational expenses");
        budgetService.updateBudget(budget2.getId(), updatedBudget);

        budgets = budgetService.getAllBudgets();
        System.out.println("All Budgets after update: \n" + budgets + "\n");

        budgetService.deleteBudget(budget1.getId());
        budgets = budgetService.getAllBudgets();
        System.out.println("All Budgets after deletion: \n" + budgets + "\n");

        ExpenseStream expenseStream = expenseStreamService.createExpenseStream(
                new ExpenseStream(2000.00, "Software", "Purchase software licenses"));

        createdStream.setBudgetId(budget2.getId());
        expenseStream.setBudgetId(budget2.getId());
        budgetService.addIncomeStreamToBudget(budget2.getId(), createdStream.getId());
        budgetService.addExpenseStreamToBudget(budget2.getId(), expenseStream.getId());

        budgets = budgetService.getAllBudgets();
        System.out.println("All Budgets with connections: \n" + budgets + "\n");

        service.deleteIncomeStream(1L);
        System.out.println("Deleted Income Stream with ID 1\n");
    }
}
