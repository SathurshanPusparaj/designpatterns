package org.projectx.designpatterns.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class LazySingletonPattern implements Runnable{

    private static LazySingletonPattern instance = null;

    private LazySingletonPattern() {

    }

    private static Supplier<LazySingletonPattern> supplier = () -> {
        instance = new LazySingletonPattern();
        supplier = () -> instance;
        return instance;
    };

    public static LazySingletonPattern getInstance() {
        return supplier.get();
    }

    @Override
    public void run() {
        System.out.println(LazySingletonPattern.getInstance());
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();

        IntStream.range(0, 100).forEachOrdered(value -> {
            Thread thread = new Thread(new LazySingletonPattern());
            threads.add(thread);
        });

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
