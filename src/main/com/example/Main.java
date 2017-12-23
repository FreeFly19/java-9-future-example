package com.example;

import ligualeo.LinguaLeoService;

import java.util.concurrent.Callable;

public class Main {


    public static void main(String[] args) {
        LinguaLeoService linguaLeoService = new LinguaLeoService();


        withTimeMetric("Pot word", () -> {
            String[] words = "hello world it is my first code with java nine".split(" ");
            MyFuture<String>[] futures = new MyFuture[words.length];

            for (int i = 0; i < futures.length; i++) {
                int n = i;
                futures[i] = new MyFuture<>(() -> linguaLeoService.getTranslations(words[n]).get(0));
            }

            for (MyFuture<String> future : futures) {
                System.out.println(future.get());
            }
        });



    }

    static class MyFuture<T> {
        private final Thread thread;
        private final Object[] res = new Object[1];

        public MyFuture(Callable<T> callable) {
            thread = new Thread(() -> {
                try {
                    res[0] = callable.call();
                } catch (Exception e) { /* Could happen, but I am an optimist */ }
            });
            thread.start();
        }

        public T get() {
            try {
                thread.join();
            } catch (InterruptedException e) { /* As usually */}
            return (T) res[0];
        }

    }





















    public static void withTimeMetric(String name, Runnable metric) {
        long start = System.currentTimeMillis();
        metric.run();
        System.err.println(name + ": " + (System.currentTimeMillis() - start) + "ms");
    }
}


