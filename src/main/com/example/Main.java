package com.example;

import ligualeo.LinguaLeoService;

public class Main {


    public static void main(String[] args) {
        LinguaLeoService linguaLeoService = new LinguaLeoService();


        withTimeMetric("Pot word", () -> {


            linguaLeoService.getTranslations("hello").forEach(System.out::println);
            linguaLeoService.getTranslations("world").forEach(System.out::println);
            linguaLeoService.getTranslations("it").forEach(System.out::println);
            linguaLeoService.getTranslations("is").forEach(System.out::println);
            linguaLeoService.getTranslations("my").forEach(System.out::println);
            linguaLeoService.getTranslations("first").forEach(System.out::println);
            linguaLeoService.getTranslations("code").forEach(System.out::println);
            linguaLeoService.getTranslations("with").forEach(System.out::println);
            linguaLeoService.getTranslations("java").forEach(System.out::println);
            linguaLeoService.getTranslations("nine").forEach(System.out::println);



        });

    }























    public static void withTimeMetric(String name, Runnable metric) {
        long start = System.currentTimeMillis();
        metric.run();
        System.err.println(name + ": " + (System.currentTimeMillis() - start) + "ms");
    }
}


