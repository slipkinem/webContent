package top.slipkinem.admin.realm;

import top.slipkinem.admin.po.User;
import top.slipkinem.admin.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * Created by slipkinem on 6/21/2017.
 */
public class myRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    /**
     * 为当前登陆成功的用户授予权限和角色，已经登陆成功了
     * @param principalCollection 存储各个用户
     * @return 包含roleList和permissionList的对象
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(null);
        authorizationInfo.setStringPermissions(null);
        return authorizationInfo;
    }

    /**
     * 在调用subject.login的时候，会进入，和return的info进行匹配
     * @param authenticationToken token
     * @return 要匹配的用户信息
     * @throws AuthenticationException 授权失败
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userCode = (String) authenticationToken.getPrincipal();
        User user = userService.getUserByUserCode(userCode);
        if (user != null) {
            AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserCode(), user.getPassword(), "myRealm");
            return info;
        } else {
            return null;
        }
    }
}
