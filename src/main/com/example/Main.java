package com.example;

import ligualeo.LinguaLeoService;

public class Main {


    public static void main(String[] args) {
        LinguaLeoService linguaLeoService = new LinguaLeoService();


        withTimeMetric("Pot word", () -> {
            String[] words = "hello world it is my first code with java nine".split(" ");

            for (String word : words) {
                new Thread(() -> {
                    System.out.println(linguaLeoService.getTranslations(word).get(0));
                }).start();
            }

        });


    }























    public static void withTimeMetric(String name, Runnable metric) {
        long start = System.currentTimeMillis();
        metric.run();
        System.err.println(name + ": " + (System.currentTimeMillis() - start) + "ms");
    }
}


