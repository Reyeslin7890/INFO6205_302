package com.team302;


import java.util.Collections;
import java.util.PriorityQueue;

public class GA {

    public MaxPQ<Sudoku> population = new MaxPQ<Sudoku>();

    public GA() {
    }

    public void initGeneration() {
        for (int i = 0; i < 1000; i++) {
            Sudoku s = new Sudoku();
            for (int j = 0; j < 9; j++) {
                Collections.shuffle(Collections.singletonList(Sudoku.codeFrag[1]));
            }
        }

    }

    public void crossover() {
    }

    public void mutation() {
    }

    public int result() {
        int result = 0;
        return result;
    }

    public MaxPQ<Sudoku> go() {
        return population;
    }

}
