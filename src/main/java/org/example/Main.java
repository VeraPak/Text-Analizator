package org.example;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> listForA = new ArrayBlockingQueue<>(100);
        BlockingQueue<String> listForB = new ArrayBlockingQueue<>(100);
        BlockingQueue<String> listForC = new ArrayBlockingQueue<>(100);

        MyRunnableLettersCounter counterA = new MyRunnableLettersCounter('a', listForA);
        MyRunnableLettersCounter counterB = new MyRunnableLettersCounter('b', listForB);
        MyRunnableLettersCounter counterC = new MyRunnableLettersCounter('c', listForC);

        new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                try {
                    System.out.println((i+1) + " элемент положили");
                    listForA.put(generateText("abc", 100_000));
                    listForB.put(generateText("abc", 100_000));
                    listForC.put(generateText("abc", 100_000));
                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();

        new Thread(counterA).start();
        new Thread(counterB).start();
        new Thread(counterC).start();

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}