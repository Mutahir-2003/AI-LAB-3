package lab8;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSynchronization1 {

    // Create three locks
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();
    private static final Lock lock3 = new ReentrantLock();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            // Thread 1 acquires locks in the order: lock1, lock2, lock3
            try {
                lock1.lock();
                System.out.println("Thread 1 acquired lock1");
                Thread.sleep(100);

                lock2.lock();
                System.out.println("Thread 1 acquired lock2");
                Thread.sleep(100);

                lock3.lock();
                System.out.println("Thread 1 acquired lock3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock3.unlock();
                lock2.unlock();
                lock1.unlock();
                System.out.println("Thread 1 released all locks");
            }
        });

        Thread thread2 = new Thread(() -> {
            // Thread 2 acquires locks in the order: lock1, lock2, lock3
            try {
                lock1.lock();
                System.out.println("Thread 2 acquired lock1");
                Thread.sleep(100);

                lock2.lock();
                System.out.println("Thread 2 acquired lock2");
                Thread.sleep(100);

                lock3.lock();
                System.out.println("Thread 2 acquired lock3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock3.unlock();
                lock2.unlock();
                lock1.unlock();
                System.out.println("Thread 2 released all locks");
            }
        });

        Thread thread3 = new Thread(() -> {
            // Thread 3 acquires locks in the order: lock1, lock2, lock3
            try {
                lock1.lock();
                System.out.println("Thread 3 acquired lock1");
                Thread.sleep(100);

                lock2.lock();
                System.out.println("Thread 3 acquired lock2");
                Thread.sleep(100);

                lock3.lock();
                System.out.println("Thread 3 acquired lock3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock3.unlock();
                lock2.unlock();
                lock1.unlock();
                System.out.println("Thread 3 released all locks");
            }
        });

        System.out.println("Starting threads...");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads finished execution");
    }
}

