package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
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
        BuggyAList<Integer> test = new BuggyAList<>();
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);

            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                test.addLast(randVal);
                Assert.assertEquals(test.size(), L.size());
            } else if (operationNumber == 1) {
                if (L.size() == 0)
                    continue;
                int last1 = test.getLast();
                int last2 = L.getLast();
                Assert.assertEquals(last1, last2);
            }
            else if (operationNumber == 2) {
                if (L.size() == 0)
                    continue;
                int removed1 = test.removeLast();
                int removed2 = L.removeLast();
                Assert.assertEquals(removed1, removed2);
            }
        }
    }
}




