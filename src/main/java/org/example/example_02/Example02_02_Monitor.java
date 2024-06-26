package org.example.example_02;


import lombok.extern.log4j.Log4j2;

/**
 * Пример 2.
 * Monitor. wait/notify
 */
@Log4j2
public class Example02_02_Monitor {

    public static void main(String[] args) throws InterruptedException {
        final SharedObject sharedObject = new SharedObject();

        new Thread(() -> {
            synchronized (sharedObject){
                try {
                    sharedObject.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            log.info("execute");
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            synchronized (sharedObject){
                log.info("setup");
                sharedObject.notifyAll();
            }
        }).start();
    }

}

class SharedObject {

}


