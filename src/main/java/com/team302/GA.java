package com.team302;

import java.util.Random;

public class GA {

    private final static int initPopulation = 1000;
    private final static int generation = 100;
    private final static double fitnessRate = 0.1;
    private final static double mutationRate = 0.3;


    private MaxPQ population = new MaxPQ();

    GA() {
    }

    private void initGeneration() {
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


    private void crossover() {
        Random r = new Random();
        // Get 2 gene codes from population to crossover
        int[] code1 = population.pq.get(r.nextInt(population.N) + 1).code.clone();
        int[] code2 = population.pq.get(r.nextInt(population.N) + 1).code.clone();
        int[] code3 = new int[Sudoku.unused];
        int[] code4 = new int[Sudoku.unused];
//        for (int i = 0; i < 7; i++) System.out.print(code1[i]);
//        System.out.println();
//        for (int i = 0; i < 7; i++) System.out.print(code2[i]);
//        System.out.println();
        // Randomly generate positions to crossover
        boolean[] cross = new boolean[Sudoku.unused];
        for (int i = 0; i < Sudoku.unused; i++) cross[i] = r.nextBoolean();

        // Crossover
        for (int i = 0; i < Sudoku.unused; i++)
            if (cross[i]) {
                code3[i] = code2[i];
                code4[i] = code1[i];
            }

//        for (int i = 0; i < 7; i++) System.out.print(code3[i]);
//        System.out.println();
//        for (int i = 0; i < 7; i++) System.out.print(code4[i]);
//        System.out.println();

        for (int i = 0; i < 9; i++) {
            int start = Sudoku.index[i];
            int end = Sudoku.index[i + 1];
            int[] gene1 = new int[10];
            int[] gene2 = new int[10];

            for (int j = start; j < end; j++)
                if (cross[j]) {
                    gene1[code3[j]] = code4[j];
                    gene2[code4[j]] = code3[j];
                }

            for (int j = start; j < end; j++) {
                if (!cross[j] && gene1[code1[j]] == 0) {
                    gene1[code1[j]] = code2[j];
                    code3[j] = code1[j];
                }
                if (!cross[j] && gene2[code2[j]] == 0) {
                    gene2[code2[j]] = code2[j];
                    code4[j] = code2[j];
                }
            }


            for (int j = start; j < end; j++) {
                if (code3[j] == 0) {
                    int k = code1[j];
                    while (gene1[k] != 0) k = gene1[k];
                    code3[j] = k;
                }
                if (code4[j] == 0) {
                    int k = code2[j];
                    while (gene2[k] != 0) k = gene2[k];
                    code4[j] = k;
                }
            }

        }

//        for (int i = 0; i < 7; i++) System.out.print(code3[i]);
//        System.out.println();
//        for (int i = 0; i < 7; i++) System.out.print(code4[i]);
//        System.out.println();System.out.println();

        Sudoku s1 = new Sudoku();
        Sudoku s2 = new Sudoku();
        s1.code = code3;
        s2.code = code4;
        s1.fitness();
        s2.fitness();
        population.insert(s1);
        population.insert(s2);
    }

    private void mutation() {
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

    private void select() {
        MaxPQ nextGen = new MaxPQ();
        int aliveNum = Math.min((int) Math.round(population.N * fitnessRate),10000);
        while (aliveNum-- > 0) {
            //System.out.print(population.getMax().score());
            nextGen.insert(population.delMax());
        }
        population = nextGen;
    }

    private void printMax() {
        Sudoku s = population.getMax();
        System.out.println(s.fitness()+" "+s.score());
        for (int[] row : s.codeExpression()) {
            for (int i : row) System.out.print(i);
            System.out.println();
        }
    }


    public void go() {
        initGeneration();
        int curgeneration = 0;
        //printMax();
        while (curgeneration++ < generation) {
            int k = population.N;
            System.out.print(curgeneration+" ");
            //System.out.println("A:" + population.N);
            for (int i = 0; i < k * Math.log(k); i++) crossover();
            //System.out.println("B:" + population.N);
            mutation();
            //System.out.println("C:" + population.N);
            select();
            //System.out.println("D:" + population.N);
            printMax();
        }
    }

}

