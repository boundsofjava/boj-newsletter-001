package com.boundsofjava.newsletter.higherorderrunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MixedHigherOrderRunnableExample {

    public static void demo() {
        // Create a sequential higher-order runnable, that will combine
        // runnables by making them run in sequence
        HigherOrderRunnable sequential = HigherOrderRunnable.sequential();

        // Use the sequential higher-order runnable to combine our even
        // runnables first, and then to combine our odd runnables
        // (Note that the result of combining our even runnables is also a
        // runnable, and the same occurs with our odd runnables)
        Runnable sequenceEven = sequential.combine(
                Sleeper.TWO::sleep,
                Sleeper.FOUR::sleep,
                Sleeper.SIX::sleep);
        Runnable sequenceOdd = sequential.combine(
                Sleeper.THREE::sleep,
                Sleeper.FIVE::sleep,
                Sleeper.SEVEN::sleep);

        // Create an executor capable of running 2 tasks in parallel
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Create a parallel higher-order runnable, that will combine
        // runnables by making them run in parallel
        HigherOrderRunnable parallel = HigherOrderRunnable.parallel(executor);

        // Use the parallel higher-order runnable to combine our previously
        // combined even runnables with our previously combined odd runnables
        // (Note that the result of combining these two runnables is also a
        // runnable)
        Runnable mixed2_7 = parallel.combine(sequenceEven, sequenceOdd);

        System.out.println("-----------------------------------------");
        System.out.println("MIXED");
        System.out.println("-----------------------------------------");

        long init = System.currentTimeMillis();

        // Run our fresh new runnable
        mixed2_7.run();

        long end = System.currentTimeMillis();

        System.out.println("-----------------------------------------");
        System.out.println("Total elapsed time: " + (end - init) / 1_000 + " seconds");
        System.out.println("-----------------------------------------");

        // Release executor resources
        executor.shutdown();
    }
}
