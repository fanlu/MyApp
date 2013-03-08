import com.mmtzj.service.HttpInvokerService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-8
 * Time: 下午12:01
 * To change this template use File | Settings | File Templates.
 */
public class HttpInvokerTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","applicationContext-remote.xml"});
        HttpInvokerProxyFactoryBean httpInvoker = (HttpInvokerProxyFactoryBean) ctx.getBean("httpInvokerService1");
        HttpInvokerService httpInvokerService = (HttpInvokerService) httpInvoker.getObject();
        System.out.println(httpInvokerService.getByMap("123"));
    }

    @Test
    public void test1(){
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        httpInvoker.setServiceInterface(HttpInvokerService.class);
        httpInvoker.setServiceUrl("http://localhost:8095/remoting/httpInvokerService");
        HttpInvokerService httpInvokerService = (HttpInvokerService)httpInvoker.getObject();
        System.out.println(httpInvokerService.getByMap("123"));
    }
}
