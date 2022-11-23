package cn.zxl.filecloudplus.service;

import cn.zxl.filecloudplus.entity.UserAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zxl
 * @since 2021-12-17
 */
public interface UserAdminService extends IService<UserAdmin> {
    boolean findUser(String username,String mail);
    boolean addUser(String username,String password,String mail);
}
