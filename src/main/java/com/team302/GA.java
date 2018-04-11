package com.team302;


public class GA {

    public MaxPQ<Sudoku> population = new MaxPQ<Sudoku>();

    public GA() {
    }

    public void initGeneration(){

    }

    public void crossover(){
    }

    public void mutation(){}

    public int result(){
        int result = 0;
        return result;
    }

    public MaxPQ<Sudoku> go(){
         return population;
    }

}
