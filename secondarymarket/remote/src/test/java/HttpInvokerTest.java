import com.mmtzj.service.HttpInvokerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-8
 * Time: 下午12:01
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class HttpInvokerTest {

    @Autowired
    private HttpInvokerService httpInvokerService1;

    @Test
    public void test(){
        System.out.println(httpInvokerService1.getByMap("123"));
    }

    @Test
    public void test1(){
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        httpInvoker.setServiceInterface(HttpInvokerService.class);
        httpInvoker.setServiceUrl("http://localhost:8096/HttpInvokerService");
        httpInvoker.afterPropertiesSet();
        HttpInvokerService httpInvokerService = (HttpInvokerService)httpInvoker.getObject();
        System.out.println(httpInvokerService.getByMap("123"));
    }
}
