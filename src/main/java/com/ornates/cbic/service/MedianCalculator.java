package com.ornates.cbic.service;

import java.util.List;
import java.util.Collections;

public class MedianCalculator {
    public static double calculateMedian(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0.0;
        }
        Collections.sort(numbers);
        int size = numbers.size();
        if (size % 2 == 0) {
            // Even number of elements
            return (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2.0;
        } else {
            // Odd number of elements
            return numbers.get(size / 2);
        }
    }
}

