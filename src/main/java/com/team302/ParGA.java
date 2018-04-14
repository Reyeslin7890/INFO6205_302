package com.team302;

import UI.ShowJFrame;

import java.util.concurrent.CompletableFuture;

public class ParGA {
    public GA ga1;
    public GA ga2;
    public ShowJFrame frame1;
    public ShowJFrame frame2;
    public  Sudoku s1;
    public  Sudoku s2;

    public ParGA() {
        frame1 = new ShowJFrame();
        frame2 = new ShowJFrame();
        ga1 = new GA(frame1);
        ga2 = new GA(frame2);
        s1 = new Sudoku();
        s2 = new Sudoku();
    }

    public void go(){
        CompletableFuture<Sudoku> c1 = doGA(ga1);
        CompletableFuture<Sudoku> c2 = doGA(ga2);
        CompletableFuture<Integer> c = c1.
                thenCombine(c2, (xs1, xs2) -> {
                    Integer result;
                    if(xs1.score() > xs2.score()){
                        result = 1;
                    }
                    else if(xs1.score() < xs2.score()){
                        result = 2;
                    }
                    else result = 0;

                    return result;
                });

        c.whenComplete((result, throwable) -> {
            switch (result){
                case 1:
                    frame1.field.setText("Better");
                    break;
                case 2:
                    frame2.field.setText("Better");
                    break;
                case 0:
                    frame1.field.setText("Same");
                    frame2.field.setText("Same");
                    break;
            }

        });
    }

    public CompletableFuture<Sudoku> doGA(GA ga){
        return CompletableFuture.supplyAsync(() -> ga.go());
    }
}
