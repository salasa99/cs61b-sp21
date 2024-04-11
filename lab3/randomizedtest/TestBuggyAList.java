package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
      AListNoResizing<Integer> Test1 = new AListNoResizing<>();
      BuggyAList<Integer> TestWithBug = new BuggyAList<>();

      Test1.addLast(1);
      TestWithBug.addLast(1);
      Test1.addLast(2);
      TestWithBug.addLast(2);
      Test1.addLast(3);
      TestWithBug.addLast(3);

      for (int i = 0; i < 3; i++) {
          assertEquals(Test1.removeLast(), TestWithBug.removeLast());
      }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                assertEquals(L.size(), B.size());
            } else if (operationNumber == 1) {
                // size
                if (L.size() == 0) {
                    continue;
                }
                int RemoveLastL = L.removeLast();
                int RemoveLastB = B.removeLast();
                assertEquals(RemoveLastB, RemoveLastL);
            } else if (operationNumber == 2) {
                if (L.size() == 0) {
                    continue;
                }
                int LastOfL = L.getLast();
                int LastOfB = B.getLast();
                assertEquals(LastOfL, LastOfB);
            }
        }
    }
}




