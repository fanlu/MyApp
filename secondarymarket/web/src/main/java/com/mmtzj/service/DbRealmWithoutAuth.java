package com.mmtzj.service;

import com.mmtzj.domain.User;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.stereotype.Service;

@Service("dbRealmWithoutAuth")
public class DbRealmWithoutAuth extends AuthorizingRealm {

    private final String LOGINUSER = "MMTZJ_LOGINUSER";

    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {

        if (authcToken == null) {
            throw new AuthenticationException();
        }

        // UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // JcnUser user = (JcnUser) token.getPrincipal();

        Session session = SecurityUtils.getSubject().getSession();

        User user = (User) session.getAttribute(LOGINUSER);

        // session.setAttribute(LOGINUSER, user);
        String tokenKey = user.getId() + "";

        tokenKey = new String(Base64.encodeBase64String(tokenKey.getBytes()));

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getId(), "",
                String.valueOf(user.getName()));
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }
}
