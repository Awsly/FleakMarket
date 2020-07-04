package com.ct.controller.ForegroundController;

import com.ct.model.ForegroundModel.Fc;
import com.ct.model.ForegroundModel.Sc;
import com.ct.service.ForegroundService.classifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 前台分类模块接口
 */
@RestController
@RequestMapping(value = "/classify")
public class classifyControlloer {
    @Autowired
    private classifyService classifyService;

    /**
     * 查询一级分类列表
     * @return
     */
    @RequestMapping(value = "/flnb",method = RequestMethod.GET)
    public List<Fc> selectAllclass(){
        return classifyService.selectAllclass();
    }

    /**
     * 查询二级分类列表
     * @return
     */
    @RequestMapping(value = "/selectListScById",method = RequestMethod.GET)
    public List<Sc> selectListScById(int id){
        return classifyService.selectListScById(id);
    }

    /**
     * 通过id查询一级分类对象
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectFcById",method = RequestMethod.GET)
    public Fc selectFcById(int id){
        return classifyService.selectFcById(id);
    }

    /**
     * 查询一级分类二级分类map集合
     * @return
     */
    @RequestMapping(value = "/selectFcScByList",method = RequestMethod.GET)
    public List<Map<String,Object>> selectFcScByList(){
        return classifyService.selectFcScByList();
    }

    /**
     * 查询二级分类对象
     * @return
     */
    @RequestMapping(value = "/selectScById",method = RequestMethod.GET)
    public Sc selectScById(int id){
        return classifyService.selectScById(id);
    }

    /**
     * 查询二级分类列表
     * @return
     */
    @RequestMapping(value = "/selectScList",method = RequestMethod.GET)
    public List<Sc> selectScList(){
        return classifyService.selectScList();
    }
}
