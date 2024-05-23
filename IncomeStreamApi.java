package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class IncomeStreamApi {
    private static final String BASE_URL = "http://localhost:8080/income-streams";
    private static final HttpClient httpClient = HttpClient.newBuilder().build();

    // Method to create a new income stream
    public static void createIncomeStream(double estimatedEarningsPerYear, String source, String name,
            String description) {
        String jsonPayload = String.format(
                "{\"estimatedEarningsPerYear\": %.2f, \"source\": \"%s\", \"name\": \"%s\", \"description\": \"%s\"}",
                estimatedEarningsPerYear, source, name, description);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        sendHttpRequest(request);
    }

    // Method to update an existing income stream
    public static void updateIncomeStream(long id, double estimatedEarningsPerYear, String source, String name,
            String description) {
        String jsonPayload = String.format(
                "{\"id\": %d, \"estimatedEarningsPerYear\": %.2f, \"source\": \"%s\", \"name\": \"%s\", \"description\": \"%s\"}",
                id, estimatedEarningsPerYear, source, name, description);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        sendHttpRequest(request);
    }

    // Method to delete an existing income stream
    public static void deleteIncomeStream(long id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();

        sendHttpRequest(request);
    }

    // Helper method to send HTTP request
    private static void sendHttpRequest(HttpRequest request) {
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
