package MyShiroDemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Description: IniRealm配置使用测试
 *
 * @ClassName: IniRealmTest
 * @Author: GengRui
 * @Date: 2019/9/6 11:02
 */
public class IniRealmTest {
    @Test
    public void testAuthentication() {
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //1. 构建SecurityManager 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        //2. 主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        subject.login(token);

        System.out.println("是否认证通过：" + subject.isAuthenticated());

        //3. 验证是否拥有指定角色
        subject.checkRole("admin");

        //4. 验证用户权限
        subject.checkPermissions("user:update");
    }
}