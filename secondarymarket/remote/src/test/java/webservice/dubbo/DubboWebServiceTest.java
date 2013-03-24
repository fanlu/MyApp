package webservice.dubbo;

import com.google.common.collect.Maps;
import com.mmtzj.interceptor.HeaderIntercepter;
import com.mmtzj.service.PhoneService;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-3-22
 * Time: 下午10:10
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DubboWebServiceTest {

    @Resource
    private PhoneService phoneService;

    @Test
    public void test3(){
//        HashMap<String, Object> param = Maps.newHashMap();
//        param.put("startdt", "2013-03-22 20:00:00");
//        ArrayList<HashMap<String, Object>> l = phoneService.getCDRRecord400(param);
        ArrayList<HashMap<String, Object>> l = phoneService.getMemberByPhoneInfo("4001171171","9006");
        System.out.println(l.size());
    }

    @Test
    public void test(){
        JaxWsProxyFactoryBean jaxFactory = new JaxWsProxyFactoryBean();
        jaxFactory.getInInterceptors().add(new LoggingInInterceptor());
        jaxFactory.getOutInterceptors().add(new HeaderIntercepter());
        jaxFactory.getOutInterceptors().add(new LoggingOutInterceptor());
        jaxFactory.setServiceClass(PhoneService.class);
        jaxFactory.setAddress("http://tel.m400.net/ppc/soap");
        PhoneService phoneService = (PhoneService)jaxFactory.create();
        HashMap<String, Object> param = Maps.newHashMap();
        param.put("startid", 579559);
        ArrayList<HashMap<String, Object>> l = phoneService.getCDRRecord400(param);
//        ArrayList<HashMap<String, Object>> l = phoneService.getMemberByPhoneInfo("4001171171","9006");
        System.out.println(l.size());
    }
}
