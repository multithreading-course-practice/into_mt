package org.example.example_02;


import lombok.extern.log4j.Log4j2;

import static java.lang.Thread.sleep;

/**
 * Пример 2.
 * Volatile
 */
@Log4j2
public class Example02_03_Volatile {

    static volatile boolean flag = false;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!flag){
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            log.info("execute");
        }).start();

        sleep(1000);

        new Thread(() -> {
            log.info("setup");
            flag = true;
        }).start();
    }

}



