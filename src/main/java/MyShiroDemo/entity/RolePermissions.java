package MyShiroDemo.entity;

import lombok.Data;

/**
 * Description:
 *
 * @ClassName: RolePermissions
 * @Author: GengRui
 * @Date: 2019/9/10 10:22
 */
@Data
public class RolePermissions {
    private Long id;
    private String permission;
    private String roleName;
}
