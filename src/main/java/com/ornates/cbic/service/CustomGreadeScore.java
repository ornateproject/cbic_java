package com.ornates.cbic.service;

public class CustomGreadeScore {


    // Custom grade score for custom 1
    public int c_marks1(double total) {
        int rank=0;
        if(total == 0) {
            rank=10;
        }else if(total >= 1 && total <= 5) {
            rank=7;
        }else if(total >= 6 && total <= 10) {
            rank=4;
        }else {
            rank=0;
        }
        return rank;
    }
    // Custom grade score for custom 2a
    public int c_marks2a(double total) {
        int rank=0;
        if(total >=80) {
            rank=10;
        }else if(total >= 70 && total <= 79) {
            rank=7;
        }else if(total >= 60 && total <= 69) {
            rank=4;
        }else {
            rank=2;
        }
        return rank;
    }
    // Custom grade score for custom 2b
    public int c_marks2b(double total) {
        int rank=0;
        if(total <=10) {
            rank=10;
        }else if(total >= 11 && total <= 20) {
            rank=7;
        }else if(total >= 21 && total <= 30) {
            rank=4;
        }else {
            rank=2;
        }
        return rank;
    }
    // Custom grade score for custom 2c
    public int c_marks2c(double total) {
        int rank=0;
        if(total >=30) {
            rank=10;
        }else if(total >= 20 && total <= 29) {
            rank=7;
        }else if(total >= 10 && total <= 19) {
            rank=4;
        }else {
            rank=2;
        }
        return rank;
    }
    // Custom grade score for custom 3a
    public int c_marks3a(double total) {
        int rank=0;
        if(total >=80) {
            rank=10;
        }else if(total >= 70 && total <= 79) {
            rank=7;
        }else if(total >= 60 && total <= 69) {
            rank=4;
        }else {
            rank=2;
        }
        return rank;
    }
    // Custom grade score for custom 3b
    public int c_marks3b(double total) {
        int rank=0;
        if(total <=10) {
            rank=10;
        }else if(total >= 10 && total <= 20) {
            rank=7;
        }else if(total >= 21 && total <= 30) {
            rank=4;
        }else {
            rank=2;
        }
        return rank;
    }
    // Custom grade score for custom 3c
    public int c_marks3c(double total) {
        int rank=0;
        if(total >=30) {
            rank=10;
        }else if(total >= 20 && total <= 29) {
            rank=7;
        }else if(total >= 10 && total <= 19) {
            rank=4;
        }else {
            rank=2;
        }
        return rank;
    }
    // Custom grade score for custom 4a
    public int c_marks4a(double total) {
        int rank=0;
        if(total <=10) {
            rank=10;
        }else if(total >= 11 && total <= 15) {
            rank=7;
        }else if(total >= 16 && total <= 20) {
            rank=4;
        }else {
            rank=2;
        }
        return rank;
    }
    // Custom grade score for custom 4b
    public int c_marks4b(double total) {
        int rank=0;
        if(total <=10) {
            rank=10;
        }else if(total >= 11 && total <= 15) {
            rank=7;
        }else if(total >= 16 && total <= 20) {
            rank=4;
        }else {
            rank=2;
        }
        return rank;
    }
    // Custom grade score for custom 4c
    public int c_marks4c(double total) {
        int rank=0;
        if(total >=30) {
            rank=10;
        }else if(total >= 20 && total <= 29) {
            rank=7;
        }else if(total >= 10 && total <= 19) {
            rank=4;
        }else {
            rank=2;
        }
        return rank;
    }
    // Custom grade score for custom 5a
    public int c_marks5a(double total) {
        int rank=0;
        if(total >=30) {
            rank=10;
        }else if(total >= 20 && total <= 29) {
            rank=7;
        }else if(total >= 10 && total <= 19) {
            rank=4;
        }else {
            rank=2;
        }
        return rank;
    }
    // Custom grade score for custom 5B
    public int c_marks5b(double total) {
        int rank=0;
        if(total == 0) {
            rank=10;
        }else if(total >= 1 && total <= 10) {
            rank=7;
        }else if(total >= 11 && total <= 20) {
            rank=4;
        }else {
            rank=0;
        }
        return rank;
    }
    // Custom grade score for custom 5c
    public int c_marks5c(double total) {
        int rank=0;
        if(total == 0) {
            rank=10;
        }else if(total >= 1 && total <= 5) {
            rank=7;
        }else if(total >= 6 && total <= 10) {
            rank=4;
        }else {
            rank=0;
        }
        return rank;
    }
    // Custom grade score for custom 6A
    public int c_marks6a(double total) {
        int rank=0;
        if(total >= 30) {
            rank=10;
        }else if(total >= 25 && total <= 29) {
            rank=7;
        }else if(total >= 20 && total <= 24) {
            rank=4;
        }else {
            rank=0;
        }
        return rank;
    }
    // Custom grade score for custom 6B
    public int c_marks6b(double total) {
        int rank=0;
        if(total <=20 ) {
            rank=10;
        }else if(total >= 21 && total <= 30) {
            rank=7;
        }else if(total >= 31 && total <= 40) {
            rank=4;
        }else {
            rank=0;
        }
        return rank;
    }
    // Custom grade score for custom 6C
    public int c_marks6c(double total) {
        int rank=0;
        if(total >=20 ) {
            rank=10;
        }else if(total >= 15 && total <= 19) {
            rank=7;
        }else if(total >= 10 && total <= 14) {
            rank=4;
        }else {
            rank=0;
        }
        return rank;
    }
    // Custom grade score for custom 6D
    public int c_marks6d(double total) {
        int rank=0;
        if(total >=40 ) {
            rank=10;
        }else if(total >= 30 && total <= 39) {
            rank=7;
        }else if(total >= 20 && total <= 29) {
            rank=4;
        }else {
            rank=0;
        }
        return rank;
    }
    // Custom grade score for custom 6E
    public int c_marks6e(double total) {  // Doubte , check word file greade score
        int rank=0;
        if(total >=30 ) {
            rank=10;
        }else if(total >= 25 && total <= 29) {
            rank=7;
        }else if(total >= 20 && total <= 24) {
            rank=4;
        }else {
            rank=0;
        }
        return rank;
    }
    // Custom grade score for custom 6F
    public int c_marks6f(double total) {
        int rank=0;
        if(total >=30 ) {
            rank=10;
        }else if(total >= 25 && total <= 29) {
            rank=7;
        }else if(total >= 20 && total <= 24) {
            rank=4;
        }else {
            rank=0;
        }
        return rank;
    }
}