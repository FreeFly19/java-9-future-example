package com.example;

import ligualeo.LinguaLeoService;

public class Main {


    public static void main(String[] args) {
        LinguaLeoService linguaLeoService = new LinguaLeoService();


        withTimeMetric("Pot word", () -> {
            String[] words = "hello world it is my first code with java nine".split(" ");
            String[] translatedWords = new String[words.length];
            Thread[] threads = new Thread[words.length];

            for (int i = 0; i < words.length; i++) {
                int n = i;
                threads[i] = new Thread(() -> {
                    translatedWords[n] = linguaLeoService.getTranslations(words[n]).get(0);
                });
                threads[i].start();
            }

            for (int i = 0; i < threads.length; i++) {
                try {
                    threads[i].join();
                    System.out.println(translatedWords[i]);
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


