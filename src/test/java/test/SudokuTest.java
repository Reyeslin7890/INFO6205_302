package test;

import com.team302.Sudoku;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SudokuTest {

    //test for fitness()
    @Test
    public void test1(){
        Sudoku s = new Sudoku();
        s.setCode(new int[]{6,8,1,5,3,2,9,4,1,3,6,6,7,2,8,9,8,6,2,4,2,6,1,4,3,7,3,4,9,6,4,2,9,3,8,5,1,7,3,5,7,8,2,4,5,8,7,2,4,3,9});
        assertEquals(144,s.fitness());

        Sudoku sudoku = new Sudoku();
        sudoku.setCode(new int[]{8,1,6,5,2,3,9,4,1,3,6,6,7,2,8,9,6,8,4,2,2,6,1,3,4,7,3,4,9,6,5,2,9,4,7,3,8,1,8,5,7,3,2,4,3,7,4,2,8,5,9});
        assertEquals(133,sudoku.fitness());
    }

    //test for score()
    @Test
    public void test2(){
        Sudoku s = new Sudoku();
        s.setCode(new int[]{6,8,1,5,3,2,9,4,1,3,6,6,7,2,8,9,8,6,2,4,2,6,1,4,3,7,3,4,9,6,4,2,9,3,8,5,1,7,3,5,7,8,2,4,5,8,7,2,4,3,9});
        assertEquals(2849,s.score());

        Sudoku sudoku = new Sudoku();
        sudoku.setCode(new int[]{8,1,6,5,2,3,9,4,1,3,6,6,7,2,8,9,6,8,4,2,2,6,1,3,4,7,3,4,9,6,5,2,9,4,7,3,8,1,8,5,7,3,2,4,3,7,4,2,8,5,9});
        assertEquals(0,sudoku.score());
    }

    //test for hashcode()
    @Test
    public void test3(){
        Sudoku s = new Sudoku();
        s.setCode(new int[]{6,8,1,5,3,2,9,4,1,3,6,6,7,2,8,9,8,6,2,4,2,6,1,4,3,7,3,4,9,6,4,2,9,3,8,5,1,7,3,5,7,8,2,4,5,8,7,2,4,3,9});
        assertEquals(-7081910511230699004L, s.hashcode());

        Sudoku sudoku = new Sudoku();
        sudoku.setCode(new int[]{8,1,6,5,2,3,9,4,1,3,6,6,7,2,8,9,6,8,4,2,2,6,1,3,4,7,3,4,9,6,5,2,9,4,7,3,8,1,8,5,7,3,2,4,3,7,4,2,8,5,9});
        assertEquals(4293154329287274216L,sudoku.hashcode());
    }

}