package multithreading;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

    CompletableFuture<String> getUserName(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            Random random = new Random();
            try {
                Thread.sleep((random.nextInt(5) + 1)*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Name-" + userId;
        });
    }

    CompletableFuture<String> getUserProfile(String userName) {
        return CompletableFuture.supplyAsync(() -> {
            Random random = new Random();
            try {
                Thread.sleep((random.nextInt(5) + 1)*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Profile-" + userName;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureExample completableFutureExample = new CompletableFutureExample();
        CompletableFuture<String> userNameF =  completableFutureExample.getUserName(1);
        System.out.println(userNameF.thenCompose(completableFutureExample::getUserProfile).get());

        userNameF =  completableFutureExample.getUserName(1);
        System.out.println(userNameF.thenCombine(completableFutureExample.getUserProfile("x"), (userName, userProfile) -> {
            return userName + userProfile;
        }).get());

        CompletableFuture<?> completableFutures = CompletableFuture.allOf(completableFutureExample.getUserName(1),
            completableFutureExample.getUserProfile("X"));
        completableFutures.join();
        completableFutures.thenRun(() -> System.out.println("Complete All of " + LocalDateTime.now()));

        CompletableFuture<Object> any = CompletableFuture.anyOf(completableFutureExample.getUserName(1),
            completableFutureExample.getUserProfile("X"));
        System.out.println(any.get() + " " + LocalDateTime.now());

    }
}
