package com.example;

import ligualeo.LinguaLeoService;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        LinguaLeoService linguaLeoService = new LinguaLeoService();


        withTimeMetric("Pot word", () -> {
            Arrays.stream("hello world it is my first code with java nine".split(" "))
                    .parallel()
                    .map(linguaLeoService::getTranslations)
                    .flatMap(translations -> translations.stream().limit(1))
                    .forEach(System.out::println);

        });

    }























    public static void withTimeMetric(String name, Runnable metric) {
        long start = System.currentTimeMillis();
        metric.run();
        System.err.println(name + ": " + (System.currentTimeMillis() - start) + "ms");
    }
}


