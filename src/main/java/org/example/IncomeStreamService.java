package org.example;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class IncomeStreamService {

    private final List<IncomeStream> incomeStreams = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<IncomeStream> getAllIncomeStreams() {
        return new ArrayList<>(incomeStreams);
    }

    public Optional<IncomeStream> getIncomeStreamById(Long id) {
        return incomeStreams.stream().filter(stream -> stream.getId() == id).findFirst();
    }

    public IncomeStream createIncomeStream(IncomeStream incomeStream) {
        incomeStream.setId(counter.incrementAndGet());
        incomeStream.setInsertDate(new Date());
        incomeStream.setUpdateDate(new Date());
        incomeStreams.add(incomeStream);
        System.out.println("Income Stream created...");
        System.out.println(incomeStream);
        return incomeStream;
    }

    public Optional<IncomeStream> updateIncomeStream(Long id, IncomeStream incomeStreamDetails) {
        return getIncomeStreamById(id).map(incomeStream -> {
            incomeStream.setEstimatedEarningsPerYear(incomeStreamDetails.getEstimatedEarningsPerYear());
            incomeStream.setSource(incomeStreamDetails.getSource());
            incomeStream.setName(incomeStreamDetails.getName());
            incomeStream.setDescription(incomeStreamDetails.getDescription());
            incomeStream.setUpdateDate(new Date());
            incomeStream.setCategoryId(incomeStreamDetails.getCategoryId());
            return incomeStream;
        });
    }

    public boolean deleteIncomeStream(Long id) {
        return incomeStreams.removeIf(stream -> stream.getId() == id);
    }

    public List<IncomeStream> getIncomeStreamsByMinEarnings(double minEarnings) {
        return incomeStreams.stream()
                .filter(stream -> stream.getEstimatedEarningsPerYear() >= minEarnings)
                .collect(Collectors.toList());
    }

    public Optional<IncomeStream> assignCategoryToIncomeStream(Long id, Long categoryId) {
        return getIncomeStreamById(id).map(incomeStream -> {
            incomeStream.setCategoryId(categoryId);
            return incomeStream;
        });
    }

    public List<IncomeStream> getIncomeStreamsByCategoryId(Long categoryId) {
        return incomeStreams.stream()
                .filter(stream -> categoryId.equals(stream.getCategoryId()))
                .collect(Collectors.toList());
    }

    public List<IncomeStream> getIncomeStreamsBySource(String source) {
        return incomeStreams.stream().filter(stream -> stream.getSource().equalsIgnoreCase(source))
                .collect(Collectors.toList());
    }

    public double getTotalEstimatedEarnings() {
        return incomeStreams.stream().mapToDouble(IncomeStream::getEstimatedEarningsPerYear).sum();
    }

    public double getPercentageTowardsGoal(double goal) {
        double totalEarnings = getTotalEstimatedEarnings();

        return goal == 0 ? 0 : (totalEarnings / goal) * 100;
    }

    public double getAverageEstimatedEarnings() {
        return incomeStreams.stream()
                .mapToDouble(IncomeStream::getEstimatedEarningsPerYear)
                .average()
                .orElse(0.0);
    }
}