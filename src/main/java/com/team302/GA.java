package com.team302;


import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class GA {

    private final static int initPopulation = 100000;
    private final static int generation = 100;
    private static double fitnessRate = 0.1;
    private static double mutationRate = 0.5;


    public MaxPQ population = new MaxPQ();

    public GA() {
    }

    public void initGeneration() {
        Random r = new Random();
        for (int num = 0; num < initPopulation; num++) {
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

    }


    public void crossover() {
    }

    public void mutation() {
        int mutationNum = (int) Math.round(mutationRate * population.N);
        Random r = new Random();
        while (mutationNum-- > 0) {
            Sudoku s = new Sudoku();
            s.code = population.pq.get(r.nextInt(population.N) + 1).code.clone();
            int mutFrag = r.nextInt(9);
            int v = r.nextInt(Sudoku.codeFrag[mutFrag].length) + Sudoku.index[mutFrag];
            int w = r.nextInt(Sudoku.codeFrag[mutFrag].length) + Sudoku.index[mutFrag];
            int swap = s.code[v];
            s.code[v] = s.code[w];
            s.code[w] = swap;
            s.fitness();
            population.insert(s);
        }
    }

    public int result() {
        int result = 0;
        return result;
    }

    public void go() {
        initGeneration();
        mutation();

        Sudoku s = population.getMax();
        System.out.println(s.fitness());
        for (int[] row : s.codeExpression()) {
            for (int i : row) System.out.print(i + " ");
            System.out.println();
        }
    }

}
