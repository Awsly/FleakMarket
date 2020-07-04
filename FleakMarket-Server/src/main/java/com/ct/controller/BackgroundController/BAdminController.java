package com.ct.controller.BackgroundController;

import com.ct.model.ForegroundModel.Admin;
import com.ct.service.BackgroundService.BAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/Badmin")
public class BAdminController {

    @Autowired
    private BAdminService Service;

    /**
     * 查询管理员信息列表
     * @return
     */
    @RequestMapping(value = "/selectAdminList",method = RequestMethod.POST)
    public List<Admin> selectAdminList(@RequestBody Admin admin){
        return Service.selectAdminList(admin);
    }

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    @RequestMapping(value = "/updateAdminById",method = RequestMethod.POST)
    public int updateAdminById(@RequestBody Admin admin){
        return Service.updateAdminById(admin);
    }

    /**
     * 删除管理员信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteAdminById",method = RequestMethod.GET)
    public int deleteAdminById(int id){
        return Service.deleteAdminById(id);
    }

    /**
     * 管理员登录
     * @return
     */
    @RequestMapping(value = "/selectAdminByUser",method = RequestMethod.POST)
    public Admin selectAdminByUser(@RequestBody Admin admin){
        return Service.selectAdminByUser(admin);
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @RequestMapping(value = "/InsertAdminByAdmin",method = RequestMethod.POST)
    public int InsertAdminByAdmin(@RequestBody Admin admin){
        return Service.InsertAdminByAdmin(admin);
    }
}
