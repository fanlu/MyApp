package com.mmtzj.handler;
import com.mmtzj.thrift.gen.User;
import com.mmtzj.thrift.gen.UserNotFound;
import com.mmtzj.thrift.gen.UserService;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceHandler implements UserService.Iface {
	@Override
	public User getUser(String loginName) throws UserNotFound, TException {
		if (!"login1".equals(loginName)) {
			UserNotFound e = new UserNotFound("User not Found!");
			throw e;
		}
		User user = new User();
		user.setUserId(100);
		user.setLoginName("login1");
		user.setPassword("pwd1");
		user.setName("user1");
		return user;
	}
	@Override
	public List<User> getUsers() throws TException {
		List<User> list = new ArrayList<User>();
		User user = new User();
		user.setUserId(100);
		user.setLoginName("login1");
		user.setPassword("pwd1");
		user.setName("user1");
		list.add(user);
		User user2 = new User();
		user2.setUserId(200);
		user2.setLoginName("login2");
		user2.setPassword("pwd2");
		user2.setName("user2");
		list.add(user2);
		return list;
	}
}