package MyShiroDemo.entity;

import lombok.Data;

/**
 * Description:
 *
 * @ClassName: User
 * @Author: GengRui
 * @Date: 2019/9/10 9:16
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
}