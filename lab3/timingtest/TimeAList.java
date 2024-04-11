package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> Num = new AList<>();
        AList<Double> Time = new AList<>();
        AList<Integer> Ops = new AList<>();
        int basicNum = 1000;


        for (int i = 0; i < 8; i++) {
            AList<Integer> Test = new AList<>();
            int ops = 0;
            Stopwatch sw = new Stopwatch();

            for (int j = 0; j < basicNum; j++) {
                Test.addLast(j);
                ops++;
            }
            double time = (double) sw.elapsedTime();

            Num.addLast(basicNum);
            Time.addLast(time);
            Ops.addLast(ops);
            basicNum *= 2;
        }
        printTimingTable(Num, Time, Ops);
    }
}
