package com.team302;

import java.util.ArrayList;

public class MaxPQ{

    public ArrayList<Sudoku> pq = new ArrayList<Sudoku>();
    public int N;

    public MaxPQ() {
    }

    public boolean isEmpty(){
        return N==0;
    }
    public void insert(Sudoku sudoku){

    }

    public Sudoku delMax(){
        return pq.get(1);
    }

    private void swim(){}
    private void sink(){}
    private Boolean less(int i, int j){
        return true;
    }
    private void exch(int i, int j){
        Sudoku t = pq.get(i);
        pq.set(i,pq.get(j));
        pq.set(j, t);
    }
}
