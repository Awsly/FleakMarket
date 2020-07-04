package com.ct.mapper.BackgroundMapper;

import com.ct.model.ForegroundModel.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BAdminMapper {
    //查询管理员用户列表
    List<Admin> selectAdminList(Admin admin);
    //修改管理员信息
    int updateAdminById(Admin admin);
    //删除管理员信息
    int deleteAdminById(int id);
    //管理员登录
    Admin selectAdminByUser(Admin admin);
    //添加管理员
    int InsertAdminByAdmin(Admin admin);
}
