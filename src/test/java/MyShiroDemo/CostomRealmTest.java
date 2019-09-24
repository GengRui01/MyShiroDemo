package MyShiroDemo;

import MyShiroDemo.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: 自定义Realm测试
 *
 * @ClassName: CostomRealmTest
 * @Author: GengRui
 * @Date: 2019/9/10 14:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CostomRealmTest {
    @Test
    public void testAuthentication1() {
        CustomRealm customRealm = new CustomRealm();

        //1. 构建SecurityManager 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        //2. 主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        subject.login(token);

        System.out.println("是否认证通过：" + subject.isAuthenticated());

        subject.checkPermission("user:add");
        subject.checkRole("admin");
    }
}