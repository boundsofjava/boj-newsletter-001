package com.boundsofjava.newsletter.higherorderrunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelHigherOrderRunnableExample {

    public static void demo() {
        // Create an executor capable of running 6 tasks in parallel
        ExecutorService executor = Executors.newFixedThreadPool(6);

        // Create a parallel combinator, that will combine runnables by making
        // them run in parallel
        HigherOrderRunnable parallel = HigherOrderRunnable.parallel(executor);

        // Use the parallel combinator to combine all our runnables
        // (Note that the result of combining all our runnables is also a
        // runnable)
        Runnable parallel2_7 = parallel.combine(
                Sleeper.TWO::sleep,
                Sleeper.THREE::sleep,
                Sleeper.FOUR::sleep,
                Sleeper.FIVE::sleep,
                Sleeper.SIX::sleep,
                Sleeper.SEVEN::sleep);

        System.out.println("-----------------------------------------");
        System.out.println("PARALLEL");
        System.out.println("-----------------------------------------");

        long init = System.currentTimeMillis();

        // Run our fresh new runnable
        parallel2_7.run();

        long end = System.currentTimeMillis();

        System.out.println("-----------------------------------------");
        System.out.println("Total elapsed time: " + (end - init) / 1_000 + " seconds");
        System.out.println("-----------------------------------------");

        // Release executor resources
        executor.shutdown();
    }
}
