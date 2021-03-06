package MyShiroDemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * Description: Shiro认证测试
 *
 * @ClassName: AuthenticationTest
 * @Author: GengRui
 * @Date: 2019/9/6 9:53
 */
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void before() {
        simpleAccountRealm.addAccount("admin", "123456", "admin", "user");
    }

    @Test
    public void testAuthentication() {
        // 1. 构建SecurityManager 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 2. 主体提交认证请求
        // SecurityManager认证
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        // Authenticator认证
        // 账号错了会抛UnknownAccountException异常
        // 密码错了会抛IncorrectCredentialsException异常
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        subject.login(token);
        System.out.println("是否认证通过：" + subject.isAuthenticated());
//        subject.logout();
//        System.out.println("是否认证通过：" + subject.isAuthenticated());

        // 3. Realm验证是否拥有指定角色
        // 错了会抛UnauthorizedException异常
        subject.checkRole("admin");
        subject.checkRoles("admin", "user");
    }
}
