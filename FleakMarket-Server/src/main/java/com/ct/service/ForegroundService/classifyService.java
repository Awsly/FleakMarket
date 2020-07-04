package com.ct.service.ForegroundService;

import com.ct.mapper.ForegroundMapper.classifyMapper;
import com.ct.model.ForegroundModel.Fc;
import com.ct.model.ForegroundModel.Sc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class classifyService {

    @Autowired
    classifyMapper classifyMapper;

    public List<Fc> selectAllclass() {
        return classifyMapper.selectAllclass();
    }

    public List<Sc> selectListScById(int id) {
        return classifyMapper.selectListScById(id);
    }

    public Fc selectFcById(int id) { return classifyMapper.selectFcById(id);
    }

    public List<Map<String,Object>> selectFcScByList() {
        return classifyMapper.selectFcScByList();
    }

    public Sc selectScById(int id) {
        return classifyMapper.selectScById(id);
    }

    public List<Sc> selectScList() {
        return classifyMapper.selectScList();
    }
}
