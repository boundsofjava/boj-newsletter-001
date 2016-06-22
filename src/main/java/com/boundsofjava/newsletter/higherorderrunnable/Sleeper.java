package com.boundsofjava.newsletter.higherorderrunnable;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <p>
 * Enum that simply sleeps; TWO sleeps 2 seconds, THREE sleeps 3 seconds, etc.
 * </p>
 */
public enum Sleeper {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7);

    private final int seconds;

    private Sleeper(int seconds) {
        this.seconds = seconds;
    }

    /**
     * <p>
     * Sleeps the number of seconds specified by this enum's
     * <code>seconds</code> attribute, generating a countdown output.
     * </p>
     */
    public void sleep() {
        System.out.printf("%s => Sleeping for %d seconds...%n", this, this.seconds);
        // Countdown iteration: n, n - 1, ..., 0
        IntStream.iterate(this.seconds, i -> i - 1)
                .limit(this.seconds)
                .forEachOrdered(i -> {
                    try {
                        System.out.printf("%s => %d%n", this, i);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        // Propagate interrupt status
                        Thread.currentThread().interrupt();
                        System.out.printf("%s => Interrupted!%n", this);
                    }
                });
        System.out.printf("%s => 0. Waking up...%n", this);
    }
}
