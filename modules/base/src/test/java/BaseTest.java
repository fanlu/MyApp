import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanlu on 13-12-4.
 */
public class BaseTest {


    @Test
    public void heapOOM() {

        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }

    @Test
    public void stackLeakTest() {
        stackLeak();
    }

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    @Test
    public void stackLeakByThreadTest() {
        stackLeakByThread();
    }

    /**
     * -XX:PermSize=10M -XX:MaxPermSize=10M
     */
    @Test
    public void runtimeConstantPoolOOMTest() {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            System.out.println(String.valueOf(i++).intern());
            list.add(String.valueOf(i++).intern());
        }
    }

    /**
     * -XX:PermSize=10M -XX:MaxPermSize=10M
     */
    @Test
    public void methodAreaOOMTest() {

        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);  //To change body of implemented methods use File | Settings | File Templates.
                }
            });
            enhancer.create();
        }
    }

    /**
     * -Xmx20M -XX:MaxDirectMemorySize=10M
     * @throws IllegalAccessException
     */
    @Test
    public void directMemoryOOMTest() throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true){
            unsafe.allocateMemory(1024 * 1024);
        }
    }

    private static int _1MB = 1024 * 1024;

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
     */
    @Test
    public void allocationTest(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:PretenureSizeThreshold=3145728
     */
    @Test
    public void pretenureSizeThresholdTest(){
        byte[] allocation = new byte[4 * _1MB];
    }

    @Test
    public void getArch(){
        String arch = System.getProperty("sun.arch.data.model");
        System.out.println(arch+"-bit");
    }

    public Object instance = null;



    private byte[] bigSize = new byte[_1MB * 2];

    /**
     * -XX:+PrintGCDetails
     */
    @Test
    public void referenceCountingGCTest(){

        BaseTest a = new BaseTest();
        BaseTest b = new BaseTest();
        a.instance = b;
        b.instance = a;

        a = null;
        b = null;

        System.gc();
    }
}
