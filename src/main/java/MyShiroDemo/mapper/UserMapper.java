package MyShiroDemo.mapper;

import MyShiroDemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description:
 *
 * @InterfaceName: UserMapper
 * @Author: GengRui
 * @Date: 2019/9/9 17:38
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
