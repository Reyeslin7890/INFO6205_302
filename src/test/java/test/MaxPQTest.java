package test;
import com.team302.MaxPQ;
import com.team302.Sudoku;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MaxPQTest {

    //test of method isEmpty()
    @Test
    public void test1(){
        MaxPQ pq = new MaxPQ();
        assertEquals(true, pq.isEmpty());
    }

    //rest of method chechUnique(Sudoku x)
    @Test
    public void test2(){
        MaxPQ pq = new MaxPQ();
        Sudoku x = new Sudoku();
        x.setFitness(100);
        x.setScore(100);
        Sudoku y = new Sudoku();
        y.setFitness(144);
        y.setScore(120);

        pq.insert(x);
        pq.insert(y);

        Sudoku z = new Sudoku();
        z.setFitness(100);
        z.setScore(100);
        assertEquals(false, pq.chechUnique(z));
    }

    //test of method insert(Sudoku x)
    @Test
    public void test3(){
        MaxPQ pq = new MaxPQ();
        Sudoku x = new Sudoku();
        x.setFitness(100);
        x.setScore(100);
        Sudoku y = new Sudoku();
        y.setFitness(144);
        y.setScore(120);

        pq.insert(x);
        pq.insert(y);

        Sudoku z = pq.getMax();
        assertEquals(x,z);
    }
}