package test;

import org.junit.Test;
import com.team302.Main;
import com.team302.Sudoku;
import com.team302.GA;

import static org.junit.Assert.*;

public class GATest {

    @Test
    public void test1() {
        Main.init();
        GA ga = new GA();
        Sudoku s = new Sudoku();
        s.setCode(new int[]{6, 8, 1, 5, 3, 2, 9, 4, 1, 3, 6, 6, 7, 2, 8, 9, 8, 6, 2, 4, 2, 6, 1, 4, 3, 7, 3, 4, 9, 6, 4, 2, 9, 3, 8, 5, 1, 7, 3, 5, 7, 8, 2, 4, 5, 8, 7, 2, 4, 3, 9});
        ga.population.insert(s);
        ga.mutation(s.code);
        Sudoku newS = ga.nextGeneration.getMax();
        int count = 0;
        for (int i = 0; i < s.code.length; i++)
            if (s.code[i] != newS.code[i]) count++;
        assertTrue(count == 2 || count == 0);
    }

    @Test
    public void test2() {
        Main.init();
        GA ga = new GA();
        Sudoku s1 = new Sudoku();
        s1.setCode(new int[]{6, 8, 1, 5, 3, 2, 9, 4, 1, 3, 6, 6, 7, 2, 8, 9, 8, 6, 2, 4, 2, 6, 1, 4, 3, 7, 3, 4, 9, 6, 4, 2, 9, 3, 8, 5, 1, 7, 3, 5, 7, 8, 2, 4, 5, 8, 7, 2, 4, 3, 9});
        Sudoku s2 = new Sudoku();
        s2.setCode(new int[]{8, 1, 6, 5, 2, 3, 9, 4, 1, 3, 6, 6, 7, 2, 8, 9, 6, 8, 4, 2, 2, 6, 1, 3, 4, 7, 3, 4, 9, 6, 5, 2, 9, 4, 7, 3, 8, 1, 8, 5, 7, 3, 2, 4, 3, 7, 4, 2, 8, 5, 9});
        ga.crossover(s1.code, s2.code);
        assertTrue(ga.nextGeneration.N==2);
    }

}