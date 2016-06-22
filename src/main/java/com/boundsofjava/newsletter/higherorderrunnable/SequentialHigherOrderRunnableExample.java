package com.boundsofjava.newsletter.higherorderrunnable;

public class SequentialHigherOrderRunnableExample {

    public static void demo() {
        // Create a sequential higher-order runnable, that will combine
        // runnables by making them run in sequence
        HigherOrderRunnable sequential = HigherOrderRunnable.sequential();

        // Use the sequential higher-order runnable to combine all our runnables
        // (Note that the result of combining all our runnables is also a
        // runnable)
        Runnable sequence2_7 = sequential.combine(
                Sleeper.TWO::sleep,
                Sleeper.THREE::sleep,
                Sleeper.FOUR::sleep,
                Sleeper.FIVE::sleep,
                Sleeper.SIX::sleep,
                Sleeper.SEVEN::sleep);

        System.out.println("-----------------------------------------");
        System.out.println("SEQUENTIAL");
        System.out.println("-----------------------------------------");

        long init = System.currentTimeMillis();

        // Run our fresh new runnable
        sequence2_7.run();

        long end = System.currentTimeMillis();

        System.out.println("-----------------------------------------");
        System.out.println("Total elapsed time: " + (end - init) / 1_000 + " seconds");
        System.out.println("-----------------------------------------");
    }
}
