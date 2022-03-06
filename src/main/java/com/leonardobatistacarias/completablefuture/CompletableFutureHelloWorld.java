package com.leonardobatistacarias.completablefuture;

import com.leonardobatistacarias.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.leonardobatistacarias.util.CommonUtil.delay;
import static com.leonardobatistacarias.util.CommonUtil.startTimer;
import static com.leonardobatistacarias.util.CommonUtil.timeTaken;
import static com.leonardobatistacarias.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {

    private HelloWorldService hws;

    public CompletableFutureHelloWorld(HelloWorldService hws) {
        this.hws = hws;
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(() -> hws.helloWorld())
                .thenApply(String::toUpperCase);
//                .join();
    }

    public String helloWorld_approach1() {
        String hello = hws.hello();
        String world = hws.world();
        return hello + world;
    }

    public String helloWorld_multiple_async_calls() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync((() -> hws.hello()));
        CompletableFuture<String> world = CompletableFuture.supplyAsync((() -> hws.world()));

        String helloWorld = hello
                .thenCombine(world, (h, w) -> h+w)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        return helloWorld;
    }

    public String helloWorld_3_async_calls() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync((() -> hws.hello()));
        CompletableFuture<String> world = CompletableFuture.supplyAsync((() -> hws.world()));
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String helloWorld = hello
                .thenCombine(world, (h, w) -> h+w)
                .thenCombine(hiCompletableFuture, (previous, current) -> previous+current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        return helloWorld;
    }

    public String helloWorld_4_async_calls() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync((() -> hws.hello()));
        CompletableFuture<String> world = CompletableFuture.supplyAsync((() -> hws.world()));
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        CompletableFuture<String> myNameCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " My name is Leo!";
        });

        String helloWorld = hello
                .thenCombine(world, (h, w) -> h+w)
                .thenCombine(hiCompletableFuture, (previous, current) -> previous+current)
                .thenCombine(myNameCompletableFuture, (previous, current) -> previous+current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        return helloWorld;
    }

    public CompletableFuture<String> helloWorld_thenCompose() {
        return CompletableFuture.supplyAsync(hws::hello)
                .thenCompose((previous) -> hws.worldFuture(previous));
    }

    public static void main(String[] args) {

        HelloWorldService hws = new HelloWorldService();

        CompletableFuture.supplyAsync(() -> hws.helloWorld())
                .thenApply(String::toUpperCase)
                .thenAccept((result) -> {
                    log("Result is " + result);
                })
                .join();
        log("Done!");
    }

}
