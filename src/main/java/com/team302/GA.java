package com.team302;


import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class GA {

    public MaxPQ population = new MaxPQ();

    public GA() {
    }

    public void initGeneration() {
        Random r = new Random();
        for (int num = 0; num < 10; num++) {
            Sudoku s = new Sudoku();
            int ind = 0;
            for (int[] frag : Sudoku.codeFrag) {
                for (int i = 0; i < frag.length; i++) {
                    int j = r.nextInt(frag.length);
                    int swap = frag[i];
                    frag[i] = frag[j];
                    frag[j] = swap;
                }
                for (int aFrag : frag) s.code[ind++] = aFrag;
            }
            s.fitness();
            population.insert(s);
        }
        Sudoku s = population.getMax();
        System.out.println(s.fitness());
        for (int[] row : s.codeExpression()) {
            for (int i : row) System.out.print(i + " ");
            System.out.println();
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

    public MaxPQ go() {
        return population;
    }

}
