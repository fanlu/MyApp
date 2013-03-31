package com.mmtzj.disruptor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-3-31
 * Time: 下午6:02
 * To change this template use File | Settings | File Templates.
 */
public class ThreadPoolExecutorDemo {
//    final BlockingQueue<Runnable> queue = new SynchronousQueue<Runnable>();
    final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(15);
    final ThreadPoolExecutor exexutor = new ThreadPoolExecutor(10, 600, 30, TimeUnit.SECONDS, queue, Executors.defaultThreadFactory());
    final AtomicInteger completeTask = new AtomicInteger(0);
    final AtomicInteger rejectedTask = new AtomicInteger(0);
    static long beginTime;
    final int count = 1000;

    public static void main(String[] args){
        beginTime = System.currentTimeMillis();
        ThreadPoolExecutorDemo demo = new ThreadPoolExecutorDemo();
        demo.start();
    }

    private void start() {
        CountDownLatch latch = new CountDownLatch(count);
        CyclicBarrier barrier = new CyclicBarrier(count);
        for(int i = 0; i< count; i++){
            new Thread(new TestThread(latch, barrier)).start();
        }
        try{
            latch.await();
            exexutor.shutdownNow();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private class TestThread implements Runnable {
        private CountDownLatch latch;
        private CyclicBarrier barrier;
        public TestThread(CountDownLatch latch, CyclicBarrier barrier) {
            this.latch = latch;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (BrokenBarrierException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            try{
                exexutor.execute(new Task(latch));
            }catch (RejectedExecutionException e){
                latch.countDown();
                System.out.println(rejectedTask.incrementAndGet());
            }
        }
    }

    private class Task implements Runnable {
        private CountDownLatch latch;
        public Task(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000) ;
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            System.out.println(completeTask.incrementAndGet());
            System.out.println(System.currentTimeMillis() - beginTime);
            latch.countDown();
        }
    }
}
