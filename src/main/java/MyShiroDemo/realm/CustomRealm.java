package MyShiroDemo.realm;

import MyShiroDemo.mapper.RolePermissionsMapper;
import MyShiroDemo.mapper.UserRoleMapper;
import MyShiroDemo.entity.RolePermissions;
import MyShiroDemo.entity.User;
import MyShiroDemo.entity.UserRole;
import MyShiroDemo.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Description: 自定义Realm
 *
 * @ClassName: CustomRealm
 * @Author: GengRui
 * @Date: 2019/9/9 16:27
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RolePermissionsMapper rolePermissionsMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    // 模拟用户数据库数据
    Map<String, String> userMap = new HashMap<String, String>();

    {
        List<User> userList = userService.list();
        for (User user : userList) {
            userMap.put(user.getUsername(), user.getPassword());
        }
        super.setName("costomRealm");
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();

        // 从数据库中根据用户名获取角色数据
        Set<String> roles = getRolesByUsername(username);
        // 从数据库中根据用户名获取权限数据
        Set<String> permissions = getPermissionByUsername(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 模拟用户权限信息
     *
     * @param username
     * @return
     */
    private Set<String> getPermissionByUsername(String username) {
        Set<String> userRoleSet = getRolesByUsername(username);
        List<RolePermissions> rolePermissionsList = rolePermissionsMapper.selectList(new QueryWrapper<RolePermissions>().in("role_name", userRoleSet));

        Set<String> sets = new HashSet<String>();
        for (RolePermissions rolePermissions : rolePermissionsList) {
            sets.add(rolePermissions.getPermission());
        }
        return sets;
    }

    /**
     * 模拟根据用户名获取数据库中的角色数据
     *
     * @param username
     * @return
     */
    private Set<String> getRolesByUsername(String username) {
        List<UserRole> userRoleList = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_name", username));

        Set<String> sets = new HashSet<String>();
        for (UserRole userRole : userRoleList) {
            sets.add(userRole.getRoleName());
        }
        return sets;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1.从主体传过来的认证信息中，获取用户名
        String username = (String) authenticationToken.getPrincipal();

        // 2.通过用户名去到数据库中获取凭证
        String password = getPasswordByUsername(username);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, "costomRealm");
        return authenticationInfo;
    }

    /**
     * 模拟数据库查询凭证
     *
     * @param username
     * @return
     */
    private String getPasswordByUsername(String username) {
        return userMap.get(username);
    }
}