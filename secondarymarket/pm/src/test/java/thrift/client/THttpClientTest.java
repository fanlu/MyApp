package thrift.client;

import com.mmtzj.thrift.gen.User;
import com.mmtzj.thrift.gen.UserNotFound;
import com.mmtzj.thrift.gen.UserService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-8
 * Time: 上午10:50
 * To change this template use File | Settings | File Templates.
 */
public class THttpClientTest {

    @Test
    public void get() throws TTransportException {
        TTransport transport = new THttpClient("http://localhost:8095/user/service");
        TProtocol protocol = new TBinaryProtocol(transport);
        UserService.Client client = new UserService.Client(protocol);
        transport.open();
        System.out.println("test1");
        try {
            User user1 = client.getUser("login1");
            System.out.println("name=" + user1.getName());
        } catch (UserNotFound e) {
            System.out.println(e.getMessage());
        } catch (TException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
