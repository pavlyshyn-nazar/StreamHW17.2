package app;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Product ("Keyboard", "Electronics", 32.0f));
        products.add(new Product ("DVD", "Electronics",5.5f));
        products.add(new Product ("Bed", "Furniture",399.5f));
        products.add(new Product ("Apple", "Food", 1.5f));
        products.add(new Product ("Mango", "Food",2.0f));
        products.add(new Product ("Monitor", "Electronics",133.0f));
        products.add(new Product ("Table", "Furniture",194.5f));

        groupProductsAndPrint(products);

    }

    public static void groupProductsAndPrint(List<Product> products) {
        // Grouping
        Map<String, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        // Average in each category
        Map<String, Double> averagePriceByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)
                ));

        // Find the biggest average
        Optional<Map.Entry<String, Double>> maxAveragePriceCategory = averagePriceByCategory.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        // Print to console
        System.out.println("Categories:");
        productsByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });

        System.out.println("\nAverage in each category:");
        averagePriceByCategory.forEach((category, avgPrice) -> {
            System.out.println(category + ": " + avgPrice);
        });

        System.out.println("\nBiggest average:");
        maxAveragePriceCategory.ifPresent(category -> {
            System.out.println(category.getKey() + ": " + category.getValue());
        });
    }
}
