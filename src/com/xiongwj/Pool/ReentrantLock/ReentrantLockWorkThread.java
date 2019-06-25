package com.xiongwj.Pool.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWorkThread extends   Thread{

    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public ReentrantLockWorkThread(String name) {
        super.setName(name);
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            lock.lock();
            try {
                System.out.println(this.getName() + " " + i);
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockWorkThread test1 = new ReentrantLockWorkThread("thread1");
        ReentrantLockWorkThread test2 = new ReentrantLockWorkThread("thread2");

        test1.start();
        test2.start();
        test1.join();
        test2.join();
        System.out.println(i);
    }
}
