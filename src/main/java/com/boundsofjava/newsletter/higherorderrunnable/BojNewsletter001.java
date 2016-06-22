package com.boundsofjava.newsletter.higherorderrunnable;

public class BojNewsletter001 {

    public static void main(String[] args) {

        // Run sequential runnables combinator demo
        SequentialHigherOrderRunnableExample.demo();

        // Run parallel runnables combinator demo
        ParallelHigherOrderRunnableExample.demo();

        // Run sequential + parallel runnables combinator demo
        MixedHigherOrderRunnableExample.demo();
    }
}
