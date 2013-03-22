package thrift.client.dubbo;

import com.mmtzj.thrift.gen.UserService;
import org.apache.thrift.TException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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

    @Test
    public void test() throws TException {
        System.out.println(userService2.getUser("login1").getName());
    }
}
