package com.ct.service.BackgroundService;

import com.ct.mapper.BackgroundMapper.BAdminMapper;
import com.ct.model.ForegroundModel.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BAdminService {

    @Autowired
    private BAdminMapper Mapper;

    public List<Admin> selectAdminList(Admin admin) {
        return Mapper.selectAdminList(admin);
    }

    public int updateAdminById(Admin admin) {
        return Mapper.updateAdminById(admin);
    }

    public int deleteAdminById(int id) {
        return Mapper.deleteAdminById(id);
    }

    public Admin selectAdminByUser(Admin admin) {
        return Mapper.selectAdminByUser(admin);
    }

    public int InsertAdminByAdmin(Admin admin) {
        return Mapper.InsertAdminByAdmin(admin);
    }
}
