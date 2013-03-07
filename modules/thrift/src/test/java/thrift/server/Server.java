package thrift.server;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import thrift.gen.UserService;

public class Server {
	public static void main(String[] args) {
		try {
			UserService.Processor processor = new UserService.Processor<UserServiceHandler>(new UserServiceHandler());
			TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            // Use this for a multithreaded server
			// server = new TThreadPoolServer(processor, serverTransport);
			System.out.println("Starting the server...");
			server.serve();
		} catch (Exception x) {
			x.printStackTrace();
		}
		System.out.println("done.");
	}
}
