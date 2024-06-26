package org.example.example_01;

import lombok.extern.log4j.Log4j2;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Пример 1.
 * Способы запуска потоков
 * @see <a>https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html</a>
 */
@Log4j2
public class Example01_runThreads {


    public static void main(String[] args) {
        Thread thread1 = getRunnableThread();
        Thread thread2 = getExtendedThread();
        thread1.start();
        thread2.start();

        workload();
    }

    private static void workload() {
        log.info("Workload done");
    }

    private static Thread getExtendedThread() {
        return new Thread(){
            @Override
            public void run() {
                workload();
            }
        };
    }

    private static Thread getRunnableThread() {
        return new Thread(()-> workload());
    }

}
