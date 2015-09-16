import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-4-30
 * Time: 下午10:22
 * To change this template use File | Settings | File Templates.
 */
public class MySingletonTest {
    @Test
    public void testSingleton() {
        MySingleton my = MySingleton.getInstance();
        System.out.println(my.getS());
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Thread() {
                public void run() {
                    System.out.println(MySingleton.getInstance().getS());
                    System.out.println(MySingletonDoubleCheck.getInstance());
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
