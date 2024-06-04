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
        System.out.println("Get Income Streams by Id");
        System.out.println(incomeStreams.stream().filter(stream -> stream.getId() == id).findFirst());
        return incomeStreams.stream().filter(stream -> stream.getId() == id).findFirst();
    }

    public IncomeStream createIncomeStream(IncomeStream incomeStream) {
        incomeStream.setId(counter.incrementAndGet());
        incomeStream.setInsertDate(new Date());
        incomeStream.setUpdateDate(new Date());
        incomeStreams.add(incomeStream);
        System.out.println("Income Stream created...");
        System.out.println(incomeStreams);
        return incomeStream;
    }

    public Optional<IncomeStream> updateIncomeStream(Long id, IncomeStream incomeStreamDetails) {
        return getIncomeStreamById(id).map(incomeStream -> {
            incomeStream.setEstimatedEarningsPerYear(incomeStreamDetails.getEstimatedEarningsPerYear());
            incomeStream.setSource(incomeStreamDetails.getSource());
            incomeStream.setName(incomeStreamDetails.getName());
            incomeStream.setDescription(incomeStreamDetails.getDescription());
            incomeStream.setUpdateDate(new Date());
            System.out.println("Income Stream updated...");
            System.out.println(incomeStream);
            return incomeStream;
        });
    }

    public boolean deleteIncomeStream(Long id) {
        System.out.println("Income Stream deleted with Id: " + id);
        System.out.println(incomeStreams.removeIf(stream -> stream.getId() == id));
        return incomeStreams.removeIf(stream -> stream.getId() == id);
    }

    public List<IncomeStream> getIncomeStreamsByMinEarnings(double minEarnings) {
        return incomeStreams.stream()
                .filter(stream -> stream.getEstimatedEarningsPerYear() >= minEarnings)
                .collect(Collectors.toList());
    }
}