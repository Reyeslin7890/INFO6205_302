package com.team302;

public class Sudoku {
    public int[] code;
    private int fitness;
    private int score = 0;
    public static int[][] codeFrag = new int[9][];
    public static int unused = 0;
    public static int[] index = new int[10];

    Sudoku() {
        code = new int[unused];
        fitness = -1;
    }

    public int getFitness() {
        return fitness;
    }

    public int fitness() {
        int tot = 144;
        int[][] s = codeExpression();

        for (int i = 0; i < 9; i++) {
            boolean[] availrow = new boolean[10];
            boolean[] availcol = new boolean[10];
            for (int j = 0; j < 9; j++) {
                if (availrow[s[i][j]]) tot--;
                else availrow[s[i][j]] = true;
                if (availcol[s[j][i]]) tot--;
                else availcol[s[j][i]] = true;
            }
        }
        fitness = tot;
        return fitness;
    }

    public int[][] codeExpression() {
        int[][] s = new int[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                s[i][j] = Main.skd[i][j];

        int ind = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                for (int a = 0; a < 3; a++)
                    for (int b = 0; b < 3; b++) {
                        int row = i * 3 + a;
                        int col = j * 3 + b;
                        if (s[row][col] == 0) s[row][col] = code[ind++];
                    }
        return s;
    }

    public int score() {
        if (fitness < 144) {
            return 0;
        }
        int[][] s = codeExpression();
        score = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Math.max(Math.abs(i - 4), Math.abs(j - 4)) == 4) {
                    score = score + 6 * s[i][j];
                } else if (Math.max(Math.abs(i - 4), Math.abs(j - 4)) == 3) {
                    score = score + 7 * s[i][j];
                } else if (Math.max(Math.abs(i - 4), Math.abs(j - 4)) == 2) {
                    score = score + 8 * s[i][j];
                } else if (Math.max(Math.abs(i - 4), Math.abs(j - 4)) == 1) {
                    score = score + 9 * s[i][j];
                } else {
                    score = score + 10 * s[i][j];
                }
            }
        }

        return score;
    }
}
