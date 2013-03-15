package com.mmtzj.handler;

import com.mmtzj.thrift.gen.UserProfile;
import com.mmtzj.thrift.gen.UserStorageService;
import org.apache.thrift.TException;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-15
 * Time: 下午1:36
 * To change this template use File | Settings | File Templates.
 */
public class UserStorageServiceHandler implements UserStorageService.Iface {
    @Override
    public void store(UserProfile user) throws TException {
        System.out.println(user.getName());
    }

    @Override
    public UserProfile retrieve(int uid) throws TException {
        UserProfile u = new UserProfile();
        u.setUid(uid);
        return u;
    }
}
