package thrift.client.proxy;

import com.mmtzj.thrift.gen.User;
import com.mmtzj.thrift.gen.UserProfile;
import com.mmtzj.thrift.gen.UserService;
import com.mmtzj.thrift.gen.UserStorageService;
import org.apache.thrift.TException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-10
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserClientTest {

    @Resource
    public UserService.Iface userService1;

    @Resource
    public UserStorageService.Iface userStorageService1;

//    @Before
//    public void before() throws Exception {
//        ThriftHttpProxyFactoryBean httpInvoker = new ThriftHttpProxyFactoryBean();
//        httpInvoker.setServiceInterface(UserService.Iface.class);
//        httpInvoker.setServiceUrl("http://localhost:8096/UserService");
//        httpInvoker.afterPropertiesSet();
//        userService1 = (UserService.Iface)httpInvoker.getObject();
//    }


    @Test
    public void test() throws TException {
        List<User> users = userService1.getUsers();
        for(User user : users){
            System.out.println(user.getName());
        }
    }

    @Test
    public void test1() throws TException {
        UserProfile u = new UserProfile();
        u.setUid(123);
        u.setName("test1");
        userStorageService1.store(u);
    }

}
