package com.team302;

import UI.ShowJFrame;

import javax.swing.table.DefaultTableModel;
import java.util.Random;

public class GA {

    private final static int initPopulation = 10000;
    private final static int generation = 100;
    private final static double fitnessRateUpperBound = 0.08;
    private final static double fitnessRateLowerBound = 0.03;
    private final static int populationUB = 6000;
    private final static int populationLB = 3000;
    private final static double mutationRate = 0.5;
    private Random r = new Random();


    public MaxPQ population = new MaxPQ();
    public MaxPQ nextGeneration = new MaxPQ();

    public ShowJFrame frame;

    public GA(ShowJFrame frame) {
        this.frame = frame;
    }

    public GA() {
    }

    public void initGeneration() {
        for (int num = 0; num < initPopulation; num++) {
            Sudoku s = new Sudoku();
            int ind = 0;
            for (int[] frag : Sudoku.codeFrag) {
                //Shuffle code fragment
                for (int i = 0; i < frag.length; i++) {
                    int j = r.nextInt(frag.length);
                        int swap = frag[i];
                        frag[i] = frag[j];
                        frag[j] = swap;

                }
                for (int aFrag : frag) s.code[ind++] = aFrag;
            }
            s.fitness();
            if (s.validate()) population.insert(s);
        }

    }


    public void crossover(int[] code1, int[] code2) {
        // Get 2 gene codes from population to crossover
        if (code1.hashCode() == code2.hashCode()) return;
        int[] code3 = new int[Sudoku.unused];
        int[] code4 = new int[Sudoku.unused];

        // Randomly generate positions to crossover
        boolean[] cross = new boolean[Sudoku.unused];
        for (int i = 0; i < Sudoku.unused; i++) cross[i] = r.nextBoolean();

        // Crossover
        for (int i = 0; i < Sudoku.unused; i++)
            if (cross[i]) {
                code3[i] = code2[i];
                code4[i] = code1[i];
            }

        // Fix wrong genes
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

        Sudoku s1 = new Sudoku();
        Sudoku s2 = new Sudoku();
        s1.code = code3;
        s2.code = code4;
        s1.fitness();
        s2.fitness();
        nextGeneration.insert(s1);
        nextGeneration.insert(s2);
    }

    public void mutation(int[] code) {
        Sudoku s = new Sudoku();

        s.code = code.clone();
        int mutFrag = r.nextInt(9);
        int v = r.nextInt(Sudoku.codeFrag[mutFrag].length) + Sudoku.index[mutFrag];
        int w = r.nextInt(Sudoku.codeFrag[mutFrag].length) + Sudoku.index[mutFrag];
        int swap = s.code[v];
        s.code[v] = s.code[w];
        s.code[w] = swap;
        s.fitness();
        nextGeneration.insert(s);
    }

    public void select(double fitnessRate) {

        //Place all population into next generation
        while (population.N > 0) {
            nextGeneration.insert(population.delMax());
        }
        int aliveNum = nextGeneration.N;
        if (aliveNum > 20000)
            aliveNum = Math.max(Math.min((int) Math.round(nextGeneration.N * fitnessRate), populationUB), populationLB);
        population = new MaxPQ();
        //select sukodus to population
        while (aliveNum-- > 0 && nextGeneration.N > 0) {
            population.insert(nextGeneration.delMax());
        }
    }

    public void printMax(int curgeneration) {
        Sudoku s = population.getMax();
        System.out.println("Generation: " + curgeneration + " Population: " + population.N + " Fitness: " + s.fitness() + " Score: " + s.score());
        for (int[] row : s.codeExpression()) {
            System.out.print("      ");
            for (int i : row) System.out.print(i);
            System.out.println();
        }
    }

    public String maxText(int curgeneration) {
        Sudoku s = population.getMax();
        String text = "Generation: " + curgeneration + " Population: " + population.N + " Fitness: " + s.fitness() + " Score: " + s.score() + "\n";

        for (int[] row : s.codeExpression()) {
            text = text + "      ";
            for (int i : row) text = text + i;
            text = text + "\n";
        }
        return text;
    }

    public void maxTable(Sudoku s){
        frame.dtm = (DefaultTableModel)frame.table.getModel();
        frame.dtm.setRowCount(0);
        int[][] max = s.codeExpression();
        for(int i = 0; i < 9; i++){
            Object row[] = new Object[9];
            for(int j = 0; j < 9; j++){
                row[j] = max[i][j];
            }
            frame.dtm.addRow(row);
        }
    }

    public Sudoku go() {
//        initGeneration();
        int curgeneration = 0;
        while (curgeneration++ < generation) {
            int k = population.N;
            nextGeneration = new MaxPQ();
            //crossover
            for (int i = 0; i < k * Math.log(k)+1; i++) {
                int[] code1 = population.pq.get(r.nextInt(k) + 1).code;
                int[] code2 = population.pq.get(r.nextInt(k) + 1).code;
                crossover(code1, code2);
            }
            //mutation
            for (int i = 0; i < k * mutationRate; i++) {
                int[] code = population.pq.get(r.nextInt(k) + 1).code;
                mutation(code);
            }
            //select
            double fitnessRate = r.nextDouble() * (fitnessRateUpperBound - fitnessRateLowerBound) + fitnessRateLowerBound;
            select(fitnessRate);

            Sudoku s = population.getMax();
            frame.refresh(curgeneration, s.fitness(), s.score());
            frame.text.setText(maxText(curgeneration));
            maxTable(s);

        }

        return population.getMax();
    }

}

