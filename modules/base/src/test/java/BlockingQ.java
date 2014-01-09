import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by fanlu on 14-1-9.
 */
public class BlockingQ {
    private Object notEmpty = new Object();
    private Queue<Object> linkedList = new LinkedList<Object>();

    public Object take() throws InterruptedException {
        synchronized (notEmpty) {
            if (linkedList.size() == 0) {
                notEmpty.wait();
            }
            return linkedList.poll();
        }
    }

    public void offer(Object object) {
        synchronized (notEmpty) {
            if (linkedList.size() == 0) {
                notEmpty.notifyAll();
            }
            linkedList.add(object);
        }
    }
}
