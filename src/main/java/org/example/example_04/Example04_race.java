package org.example.example_04;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Пример 3
 * Гонка состояний
 */
@Log4j2
public class Example04_race {

    public static void main(String[] args) {
        final SharedObject sharedObject = new SharedObject();
        int threadsCount = 10;
        Thread[] threads = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(() -> {
                long timeout = System.currentTimeMillis() + 2000;
                while (sharedObject.notReady) {
                    if(System.currentTimeMillis()  >  timeout)  {
                        log.error("Timeout");
                        throw new RuntimeException("Timeout");
                    }
                }
                for (int x = 0; x < 100; x++) {
                    sharedObject.increment();
                }
            });
            threads[i].start();
        }

        sharedObject.notReady = false;
        for (int i = 0; i < threadsCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                log.error(e);
            }
        }

        log.info(sharedObject.counter);
        if(sharedObject.counter.get()!= threadsCount * 100){
            throw  new RuntimeException("Counter is not equal to threadsCount * 100 = "+sharedObject.counter);

        }
    }
}

class SharedObject {
    AtomicInteger counter = new AtomicInteger(0);
    boolean notReady = true;

    int increment() {
        return counter.incrementAndGet();
    }
}
