package MyShiroDemo.service.impl;

import MyShiroDemo.mapper.UserMapper;
import MyShiroDemo.entity.User;
import MyShiroDemo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @ClassName: UserServiceImpl
 * @Author: GengRui
 * @Date: 2019/8/12 10:06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

}
