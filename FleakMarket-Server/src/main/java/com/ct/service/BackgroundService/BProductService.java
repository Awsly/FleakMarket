package com.ct.service.BackgroundService;

import com.ct.mapper.BackgroundMapper.BProductMapper;
import com.ct.model.ForegroundModel.Product;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BProductService {

    @Autowired
    private BProductMapper mapper;

    public List<Map<String,Object>> selectProductList(Product pro) {
        return mapper.selectProductList(pro);
    }

    public int insertRecommendByPid(String pid) {
        return mapper.insertRecommendByPid(pid);
    }

    public int deleteRecommendByPid(String pid) {
        return  mapper.deleteRecommendByPid(pid);
    }

    public List<Map<String, Object>> selectProductCommentList(Product pro) {
        return mapper.selectProductCommentList(pro);
    }
}
