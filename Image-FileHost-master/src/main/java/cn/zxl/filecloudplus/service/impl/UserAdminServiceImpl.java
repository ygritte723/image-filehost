package cn.zxl.filecloudplus.service.impl;

import cn.zxl.filecloudplus.entity.UserAdmin;
import cn.zxl.filecloudplus.dao.UserAdminMapper;
import cn.zxl.filecloudplus.service.UserAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zxl
 * @since 2021-12-17
 */
@Service
public class UserAdminServiceImpl extends ServiceImpl<UserAdminMapper, UserAdmin> implements UserAdminService {

    @Autowired
    @Resource
    UserAdminMapper mapper;

    @Override
    public boolean findUser(String username, String mail) {
        boolean flag=false;
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("username",username);
        QueryWrapper wrapper1=new QueryWrapper();
        wrapper1.eq("user_email",mail);
        if((mapper.selectList(wrapper1).size()==0)
                &&(mapper.selectList(wrapper).size()==0)){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean addUser(String username, String password, String mail) {
        boolean flag=false;

        return flag;
    }
}
