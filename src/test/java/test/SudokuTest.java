package test;

import com.team302.Sudoku;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SudokuTest {

    //test for fitness()
    @Test
    public void test1(){
//        int[][] s = {{1,5,9,7,8,4,6,3,2},
//                {2,3,7,6,5,9,1,8,4},
//                {8,6,4,3,1,2,9,7,5},
//                {4,1,3,8,2,7,5,9,6},
//                {5,7,2,1,9,6,8,4,3},
//                {9,8,6,5,4,3,2,1,7},
//                {6,4,1,2,7,8,3,5,9},
//                {7,2,5,9,3,1,4,6,8},
//                {3,9,8,4,6,5,7,2,1}};
//        assertEquals(144,s.f);
        Sudoku s = new Sudoku();
        s.setCode(new int[]{6,8,1,5,3,2,9,4,1,3,6,6,7,2,8,9,8,6,2,4,2,6,1,4,3,7,3,4,9,6,4,2,9,3,8,5,1,7,3,5,7,8,2,4,5,8,7,2,4,3,9});
        assertEquals(144,s.fitness());
    }

    //test for score() 不知道问题在哪
    @Test
    public void test2(){
        Sudoku s = new Sudoku();
        s.setCode(new int[]{6,8,1,5,3,2,9,4,1,3,6,6,7,2,8,9,8,6,2,4,2,6,1,4,3,7,3,4,9,6,4,2,9,3,8,5,1,7,3,5,7,8,2,4,5,8,7,2,4,3,9});
        assertEquals(2849,s.score());
    }

    //test for hashcode()
    @Test
    public void test3(){
        Sudoku s = new Sudoku();
        s.setCode(new int[]{6,8,1,5,3,2,9,4,1,3,6,6,7,2,8,9,8,6,2,4,2,6,1,4,3,7,3,4,9,6,4,2,9,3,8,5,1,7,3,5,7,8,2,4,5,8,7,2,4,3,9});
        assertEquals(-7081910511230699004L, s.hashcode());
    }

}