package org.example.example_02;


import lombok.extern.log4j.Log4j2;

/**
 * Пример 2.
 * Синхронизировать потоки. Сперва должен завершить свою работу поток setup, затем запускаем execute.
 * Ожидание через join. Работает ли?
 */
@Log4j2
public class Example02_01_Join {

    public static void main(String[] args) throws InterruptedException {
        Thread setter = new Thread(() -> {
            log.info("setup");
        });

        Thread executor = new Thread(() -> {
            log.info("started");
            try {
                setter.join();
                log.info("setter join ended");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("execute");
        });


        executor.start();
        Thread.sleep(1000);
        setter.start();
    }

}



