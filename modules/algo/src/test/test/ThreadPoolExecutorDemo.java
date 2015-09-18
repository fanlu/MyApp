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
    //最大支持 最大线程数+queue的大小
    //int corePoolSize,
//    int maximumPoolSize,
//    long keepAliveTime,
//    TimeUnit unit,
//    BlockingQueue<Runnable> workQueue,
//    ThreadFactory threadFactory
    final ThreadPoolExecutor exexutor = new ThreadPoolExecutor(10, 600, 30, TimeUnit.SECONDS, queue, Executors.defaultThreadFactory());
    final AtomicInteger completeTask = new AtomicInteger(0);
    final AtomicInteger rejectedTask = new AtomicInteger(0);
    static long beginTime;
    final int count = 1000;

    public static void main(String[] args) {
        beginTime = System.currentTimeMillis();
        ThreadPoolExecutorDemo demo = new ThreadPoolExecutorDemo();
        demo.start();
        System.out.println("被拒绝的任务数为:" + demo.rejectedTask.get());
        System.out.println("执行完成的任务数为:" + demo.completeTask.get());
        System.out.println("线程池峰值:" + demo.exexutor.getLargestPoolSize());
        System.out.println("线程池总共处理的任务数" + demo.exexutor.getCompletedTaskCount());
    }

    private void start() {
        CountDownLatch latch = new CountDownLatch(count);
        CyclicBarrier barrier = new CyclicBarrier(count);
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(new TestThread(latch, barrier));
            t.setName("第" + i + "个线程");
            t.start();
        }
        try {
            latch.await();
            exexutor.shutdownNow();
        } catch (InterruptedException e) {
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
            try {
                exexutor.execute(new Task(latch));
            } catch (RejectedExecutionException e) {
                latch.countDown();
                rejectedTask.incrementAndGet();
                System.out.println("被拒绝的任务:" + Thread.currentThread().getName());
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
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            completeTask.incrementAndGet();
            System.out.println("执行完成的任务数:" + Thread.currentThread().getName() + "任务耗时为:" + (System.currentTimeMillis() - beginTime));
            latch.countDown();
        }
    }
}
