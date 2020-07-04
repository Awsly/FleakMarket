package com.ct.mapper.ForegroundMapper;

import com.ct.model.ForegroundModel.Fc;
import com.ct.model.ForegroundModel.Sc;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface classifyMapper {
    //一级分类列表接口
    List<Fc> selectAllclass();
    //二级分类列表接口
    List<Sc> selectListScById(int id);
    //一级分类对象接口
    Fc selectFcById(int id);
    //一级分类二级分类全数据接口
    List<Map<String,Object>> selectFcScByList();
    //二级分类对象接口
    Sc selectScById(int id);
    //二级分类列表
    List<Sc> selectScList();
}
