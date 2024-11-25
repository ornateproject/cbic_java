package com.ornates.cbic.service;

public class TestingGradeScoreGst3b {

    public int marks3b(double total) {
        int rank=0;
        if(total>=50) {
            rank=10;
        }else if(total >= 40 && total < 50) {
            rank=7;
        }else if(total >= 30 && total < 40) {
            rank=4;
        }else {
            rank=2;
        }
        return rank;
    }
}
