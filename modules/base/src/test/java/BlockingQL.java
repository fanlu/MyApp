import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fanlu on 14-1-9.
 */
public class BlockingQL {

    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private Queue<Object> linkedList = new LinkedList<Object>();
    private int maxLength = 10;

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            if (linkedList.size() == 0) {
                notEmpty.await();
            }
            if (linkedList.size() == maxLength) {
                notFull.signalAll();
            }
            return linkedList.poll();
        } finally {
            lock.unlock();
        }
    }

    public void offer(Object object) throws InterruptedException {
        lock.lock();
        try {
            if (linkedList.size() == 0) {
                notEmpty.signalAll();
            }
            if (linkedList.size() == maxLength) {
                notFull.await();
            }
            linkedList.add(object);
        } finally {
            lock.unlock();
        }
    }

}
