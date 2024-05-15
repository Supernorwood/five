package org.example;

import java.util.List;
import java.util.ArrayList;

public class Main {

    public static final double GOAL_NUMBER = 500_000.00;

    public static void main(String[] args) {
        System.out.println("Hello five!");

        IncomeStream stream1 = new IncomeStream(10000.00, "Natgo", "Job", "java job");
        System.out.println(stream1);

        IncomeStream stream2 = new IncomeStream(20000.00, "SN", "Job", "TSE");
        System.out.println(stream2);

        // Create an ArrayList to hold IncomeStream objects
        ArrayList<IncomeStream> incomeStreams = new ArrayList<>();

        // Create an IncomeStream object (example)
        IncomeStream newIncomeStream = new IncomeStream(
                45000.0, // Estimated earnings
                "E-commerce Store", // Source
                "Online retail business", // Name
                "Selling handmade crafts and accessories"

        );

        // Add the object to the ArrayList
        System.out.println(newIncomeStream);
        System.out.println(incomeStreams);

        incomeStreams.add(stream1);
        incomeStreams.add(stream2);
        incomeStreams.add(newIncomeStream);

        System.out.println(incomeStreams);

        // Calculate total estimated earnings
        double totalEarnings = 0.0;
        for (IncomeStream incomeStream : incomeStreams) {
            totalEarnings += incomeStream.getEstimatedEarningsPerYear();
        }

        System.out.println("Total Estimated Earnings Per Year: $" + totalEarnings);
        System.out.println("This is what I am earning: $" + totalEarnings +
                "This is what I am aiming to make: $" + GOAL_NUMBER);

        // Calculate percentage (handle potential division by zero)
        double percentage = (totalEarnings / GOAL_NUMBER) * 100.0;
        if (GOAL_NUMBER == 0) {
            percentage = 0.0; // Avoid division by zero
        }

        // Format and print the percentage with two decimal places
        System.out.println("You are %.2f%% towards your goal." + percentage);

        // Calculate the distance (ensure non-negative value)
        double distance = Math.max(0.0, GOAL_NUMBER - totalEarnings);
        System.out.println("You are still $%.2f away from your goal.\n" + distance);

    }
}
