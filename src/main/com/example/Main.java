package com.example;

import ligualeo.LinguaLeoService;

public class Main {


    public static void main(String[] args) {
        LinguaLeoService linguaLeoService = new LinguaLeoService();


        withTimeMetric("Pot word", () -> {
            String[] words = "hello world it is my first code with java nine".split(" ");
            Thread[] threads = new Thread[words.length];

            for (int i = 0; i < words.length; i++) {
                int n = i;
                threads[i] = new Thread(() -> {
                    System.out.println(linguaLeoService.getTranslations(words[n]).get(0));
                });
                threads[i].start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) { /*will never happen, I hope so*/ }
            }

        });


    }























    public static void withTimeMetric(String name, Runnable metric) {
        long start = System.currentTimeMillis();
        metric.run();
        System.err.println(name + ": " + (System.currentTimeMillis() - start) + "ms");
    }
}


