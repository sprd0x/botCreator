package com.azizsaparniyazov.services;

import com.azizsaparniyazov.models.Category;
import com.azizsaparniyazov.services.utilServices.JsonReader;
import com.azizsaparniyazov.services.utilServices.JsonWriter;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//TODO Madina
public class CategoryService {
    private static GoodService goodService = new GoodService();
    private final String PATH = "src\\Categories.json";


    public Category add(Category category) {
        if (hasCategoryByCategoryNameAndUserId(category.getName(), category.getUserId()) == null) {
            List<Category> categories = read();
            categories.add(category);
            write(categories);
            return category;
        }
        return null;
    }

    public String delete(String name, String userId) {
        Category category = this.hasCategoryByCategoryNameAndUserId(name, userId);
        if (category != null) {
            List<Category> categories = read();
            categories.remove(category);
            write(categories);
            goodService.deleteGoodByCategory(category.getId());
            return "Category successfully deleted";
        }
        return "Category with this name not founded";
    }

    public Category update(UUID categoryId, String newName) {
        List<Category> categories = read();
        for (Category category : categories) {
            if (category.getId().equals(categoryId)) {
                category.setName(newName);
                write(categories);
                return category;
            }
        }
        return null;
    }

    public List<Category> showMyCategories(String userId) {
        List<Category> categories = new ArrayList<>();
        read().stream().forEach(category -> {
            if (category.getUserId().equals(userId)) categories.add(category);
        });
        return categories;
    }

    private Category hasCategoryByCategoryNameAndUserId(String categoryName, String userId) {
        return read().stream()
                .filter(category -> category.getName().equals(categoryName)
                        && category.getUserId().equals(userId))
                .findFirst()
                .orElse(null);

    }

    public List<Category> read() {
        return JsonReader.readGson(PATH, new TypeReference<List<Category>>() {
        });
    }

    public void write(List<Category> categories) {
        JsonWriter.writeGson(categories, PATH);
    }

}
