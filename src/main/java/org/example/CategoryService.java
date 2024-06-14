package org.example;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CategoryService {

    private final List<Category> categories = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categories.stream().filter(category -> category.getId() == id).findFirst();
    }

    public Category createCategory(Category category) {
        category.setId(counter.incrementAndGet());
        categories.add(category);
        System.out.println("Category created...");
        System.out.println(category);
        return category;
    }

    public Optional<Category> updateCategory(Long id, Category categoryDetails) {
        return getCategoryById(id).map(category -> {
            category.setName(categoryDetails.getName());
            category.setDescription(categoryDetails.getDescription());
            return category;
        });
    }

    public boolean deleteCategory(Long id) {
        return categories.removeIf(category -> category.getId() == id);
    }

}