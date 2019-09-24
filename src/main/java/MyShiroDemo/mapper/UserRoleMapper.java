package MyShiroDemo.mapper;

import MyShiroDemo.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description:
 *
 * @InterfaceName: UserRoleMapper
 * @Author: GengRui
 * @Date: 2019/9/10 10:38
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
