package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Main implements CommandLineRunner {

    public static final double GOAL_NUMBER = 500_000.00;

    @Autowired
    private IncomeStreamService service;

    @Override
    public void run(String... args) {
        System.out.println("Hello five!");

        IncomeStream stream1 = new IncomeStream(10000.00, "Natgo", "Job", "java job");
        System.out.println(stream1);

        IncomeStream stream2 = new IncomeStream(20000.00, "SN", "Job", "TSE");
        System.out.println(stream2);

        ArrayList<IncomeStream> incomeStreams = new ArrayList<>();

        IncomeStream newIncomeStream = new IncomeStream(
                45000.0, // Estimated earnings
                "E-commerce Store", // Source
                "Online retail business", // Name
                "Selling handmade crafts and accessories");

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

        double percentage = (totalEarnings / GOAL_NUMBER) * 100.0;
        if (GOAL_NUMBER == 0) {
            percentage = 0.0; // Avoid division by zero
        }
        System.out.println("You are " + percentage + "% towards your goal.");

        double distance = Math.max(0.0, GOAL_NUMBER - totalEarnings);
        System.out.println("You are still " + distance + " away from your goal.\n");

        service.createIncomeStream(new IncomeStream(30000.00, "New Source", "New Name", "New Description"));

        service.updateIncomeStream(1L,
                new IncomeStream(25000.00, "Updated Source", "Updated Name", "Updated Description"));

        service.deleteIncomeStream(1L);
    }
}