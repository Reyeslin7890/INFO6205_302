package com.team302;

import sun.security.x509.SubjectAlternativeNameExtension;

import java.util.ArrayList;

public class MaxPQ{

    public ArrayList<Sudoku> pq = new ArrayList<Sudoku>();
    public int N = 0;

    public MaxPQ() {
        pq.add(new Sudoku());
    }

    public boolean isEmpty(){
        return N==0;
    }
    public void insert(Sudoku x){
        pq.add(x);
        swim(++N);
    }

    public Sudoku delMax(){
        Sudoku max = pq.get(1);
        exch(1, N--);
        sink(1);
        pq.set(N+1, null);
        return max;
    }

    public Sudoku getMax(){
        return pq.get(1);
    }

    private void swim(int k){
        while(k > 1 && less(k/2, k)){
            exch(k,k/2);
            k = k/2;
        }
    }

    private void sink(int k){
        while(2*k <= N){
            int j = 2*k;
            if(j < N && less(j, j+1)){
                j++;
            }
            if(!less(k,j)){
                break;
            }
            exch(k,j);
            k = j;
        }
    }

    private Boolean less(int i, int j){
        if(pq.get(i).getFitness() < pq.get(j).getFitness()) return true;
        else return false;
    }
    private void exch(int i, int j){
        Sudoku t = pq.get(i);
        pq.set(i,pq.get(j));
        pq.set(j, t);
    }
}
