package org.example.example_3;

import lombok.extern.log4j.Log4j2;

import static java.lang.System.currentTimeMillis;

/**
 * Пример 3.
 * Прерывания
 */
@Log4j2
public class Example03_interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            long timeout = currentTimeMillis() + 1000;
            while (true && !Thread.currentThread().isInterrupted()){
               log.info("Hello World");
               if(timeout < currentTimeMillis()){
                   throw new RuntimeException("Timeout");
               }
           }
        });
        t.start();

        Thread.sleep(500);
        t.interrupt();
        //t.suspend(); //почему нельзя?
    }
}
