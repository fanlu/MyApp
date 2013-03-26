package thrift.client.dubbo;

import com.alibaba.dubbo.rpc.RpcContext;
import com.google.common.collect.Maps;
import com.mmtzj.service.AsyncDubboService;
import com.mmtzj.service.CallbackListener;
import com.mmtzj.service.CallbackService;
import com.mmtzj.thrift.gen.UserProfile;
import com.mmtzj.thrift.gen.UserService;
import com.mmtzj.thrift.gen.UserStorageService;
import org.apache.thrift.TException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-22
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DubboClientTest {

    @Resource
    private UserService.Iface userService2;

    @Resource
    private UserStorageService.Iface userStorageService2;

    @Resource
    private AsyncDubboService asyncDubboService2;

    @Resource
    private CallbackService callbackService2;

    @Test
    public void test() throws TException {
        System.out.println(userService2.getUser("login1"));
    }

    @Test
    public void test2() throws TException {
        UserProfile u = userStorageService2.retrieve(123);
        System.out.println(u.getUid());
    }

    @Test
    public void test3(){
        Map<String, Object> m = Maps.newHashMap();
        Map<String, Object> m2 = asyncDubboService2.queryForObject(m);
        System.out.println(m2);
        Future<Map<String, Object>> future = RpcContext.getContext().getFuture();
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testCallBack(){
        callbackService2.addListener("foo.bar", new CallbackListener(){
            public void changed(String msg) {
                System.out.println("callback1:" + msg);
            }
        });
    }

}
