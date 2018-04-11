package com.team302;

public class Sudoku {
    public int[] code;
    private int fitness;
    public static int[][] codeFrag = new int[9][];
    public static int unused = 0;
    public static int[] index = new int[10];

    public Sudoku() {
        code = new int[unused];
        fitness = -1;
    }

    public int fitness() {
        if (fitness > -1) return fitness;
        int tot = 144;
        int ind = 0;
        int[][] s = Main.skd;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                for (int a = 0; a < 3; a++)
                    for (int b = 0; b < 3; b++) {
                        int row = i * 3 + a;
                        int col = j * 3 + b;
                        if (s[row][col] == 0) s[row][col] = code[ind++];
                    }
        for (int i = 0; i < 9; i++) {
            boolean[] availrow = new boolean[10];
            boolean[] availcol = new boolean[10];
            for (int j = 0; j < 9; j++) {
                if (availrow[s[i][j]]) tot--;
                else availrow[s[i][j]] = true;
                if (availcol[s[i][j]]) tot--;
                else availcol[s[i][j]] = true;
            }
        }
        fitness = tot;
        return fitness;
    }
}
