package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyRunnableLettersCounter implements Runnable {
    BlockingQueue<String> queue;
    char letter;
    MyRunnableLettersCounter(char letter, BlockingQueue<String> queue){
        this.letter = letter;
        this.queue = queue;
    }

    @Override
    public void run() {
        int count = 0;
        for (int i = 0; i < 10_000; i++) {
            try {
                for (char a : queue.take().toCharArray()) {
                    if (a == letter) {
                        count++;
                    }
                }
            } catch (InterruptedException e) {
                return;
            }
            System.out.println(letter + ": " + count);
        }
    }
}
