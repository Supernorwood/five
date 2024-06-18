package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncomeStreamServiceTest {

    @InjectMocks
    private IncomeStreamService incomeStreamService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateIncomeStream() {
        IncomeStream incomeStream = new IncomeStream(50000.00, "Test Source", "Test Name", "Test Description");
        IncomeStream createdStream = incomeStreamService.createIncomeStream(incomeStream);

        assertEquals(1L, createdStream.getId());
        assertEquals(50000.00, createdStream.getEstimatedEarningsPerYear());
        assertEquals("Test Source", createdStream.getSource());
        assertEquals("Test Name", createdStream.getName());
        assertEquals("Test Description", createdStream.getDescription());
        assertTrue(createdStream.getInsertDate().before(new Date()));
        assertTrue(createdStream.getUpdateDate().before(new Date()));
    }

    @Test
    public void testGetIncomeStreamById() {
        IncomeStream incomeStream = new IncomeStream(50000.00, "Test Source", "Test Name", "Test Description");
        incomeStreamService.createIncomeStream(incomeStream);

        Optional<IncomeStream> fetchedStream = incomeStreamService.getIncomeStreamById(1L);
        assertTrue(fetchedStream.isPresent());
        assertEquals(1L, fetchedStream.get().getId());
    }
}