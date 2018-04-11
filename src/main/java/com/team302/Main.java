package com.team302;

public class Main {

    public static final int[][] skd =
            {{7, 0, 0, 9, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 5, 9, 0, 0},
                    {0, 0, 0, 2, 0, 0, 0, 8, 0},
                    {0, 0, 5, 0, 2, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 6, 4, 8},
                    {4, 1, 3, 0, 0, 0, 0, 0, 0},
                    {0, 0, 7, 0, 0, 2, 0, 9, 0},
                    {2, 0, 1, 0, 6, 0, 8, 0, 4},
                    {0, 8, 0, 5, 0, 4, 0, 1, 2}};

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                int count = 0;
                for (int a = 0; a < 3; a++)
                    for (int b = 0; b < 3; b++)
                        if (skd[i * 3 + a][j * 3 + b] == 0) {
                            Sudoku.unused++;
                            count++;
                        }
                Sudoku.index[i * 3 + j] = count;
            }

        for (int i = 1; i < 9; i++) Sudoku.index[i] += Sudoku.index[i - 1];


    }
}
