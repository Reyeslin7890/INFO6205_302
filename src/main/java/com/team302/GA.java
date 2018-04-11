package com.team302;


public class GA {

    public MinPQ<Sudoku> population = new MinPQ<Sudoku>();

    public GA() {
    }

    public void init(){
    }

    public void crossover(){
    }

    public void mutation(){}

    public int result(){
        int result = 0;
        return result;
    }

    public MinPQ<Sudoku> go(){
         return population;
    }

}
