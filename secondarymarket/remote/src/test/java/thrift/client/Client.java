package thrift.client;
import com.mmtzj.thrift.gen.User;
import com.mmtzj.thrift.gen.UserNotFound;
import com.mmtzj.thrift.gen.UserService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.Iterator;
import java.util.List;

public class Client {
	public static void main(String[] args) {
		try {
			TTransport transport = new TSocket("localhost", 9090);
			TProtocol protocol = new TBinaryProtocol(transport);
			UserService.Client client = new UserService.Client(protocol);
			transport.open();
			System.out.println("test1");
			try {
				User user1 = client.getUser("login1");
				System.out.println("name=" + user1.getName());
			} catch (UserNotFound e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("test2");
			try {
				User user2 = client.getUser("login10");
				System.out.println("name=" + user2.getName());
			} catch (UserNotFound e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("test3");
			List<User> list = client.getUsers();
			Iterator<User> it = list.iterator();
			while(it.hasNext()){
				User u = it.next();
				System.out.println("name=" + u.getName());
			}
			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}
}
