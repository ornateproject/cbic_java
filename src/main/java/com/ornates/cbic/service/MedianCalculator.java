package com.ornates.cbic.service;

import java.util.Collections;
import java.util.List;

public class MedianCalculator {
    public Double calculateMedian(List<Double> scores) {
        Collections.sort(scores);
        int size = scores.size();
        if (size == 0) return 0.0;
        if (size % 2 == 1) {
            return scores.get(size / 2);
        } else {
            return (scores.get(size / 2 - 1) + scores.get(size / 2)) / 2.0;
        }
    }
}
