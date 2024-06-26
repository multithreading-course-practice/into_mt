package org.example.example_05;

import lombok.extern.log4j.Log4j2;

/**
 * Пример 5.
 * Иллюстрация deadlock
 */
@Log4j2
public class Example05_deadlock {

    public static void main(String[] args) {
        String mon_a = "monitor A";
        String mon_b = "monitor B";

        Object monitors_1[] = new Object[]{mon_a, mon_b};
        Object monitors_2[] = new Object[]{mon_a, mon_b};

        new Thread(getThread(monitors_1)).start();
        new Thread(getThread(monitors_2)).start();

    }

    static Runnable getThread(Object[] minotors) {
        return () -> {
            Object a = minotors[0];
            Object b = minotors[1];
            log.info("acquire first {}", a);
            synchronized (a) {
                log.info("acquire second {}", b);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    //ignore
                }
                synchronized (b) {
                    log.info("release second {}", b);
                }
                log.info("release first {}", a);
            }
        };
    }
}


