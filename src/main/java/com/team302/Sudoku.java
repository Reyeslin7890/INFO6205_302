package com.team302;

public class Sudoku {
    public int[] code;
    public static int unused = 0;
    public static int[] index = new int[9];
    public Sudoku() {
        code = new int[unused];
    }
}
