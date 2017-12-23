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
                try {
                    System.out.println(future.get());
                } catch (Exception e) { /* As a client, I believe that LinguaLeo and mclout will never fail */ }
            }
        });



    }

    static class MyFuture<T> {
        private final Thread thread;

        private T result;
        private Exception exception;

        public MyFuture(Callable<T> callable) {
            thread = new Thread(() -> {
                try {
                    setResult(callable.call());
                } catch (Exception e) {
                    setException(e);
                }
            });
            thread.start();
        }

        public T get() throws Exception {
            thread.join();
            if (exception != null) throw exception;
            return result;
        }

        private void setResult(T res) {
            this.result = res;
        }

        private void setException(Exception exception) {
            this.exception = exception;
        }
    }





















    public static void withTimeMetric(String name, Runnable metric) {
        long start = System.currentTimeMillis();
        metric.run();
        System.err.println(name + ": " + (System.currentTimeMillis() - start) + "ms");
    }
}


