package com.team302;

public class Sudoku {
    public int[] code;
    public static int[][] codeFrag = new int[9][];
    public static int unused = 0;
    public static int[] index = new int[10];
    public Sudoku() {
        code = new int[unused];
    }
}
