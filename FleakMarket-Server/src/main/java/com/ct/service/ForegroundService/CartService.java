package com.ct.service.ForegroundService;

import com.ct.mapper.ForegroundMapper.CartMapper;
import com.ct.model.ForegroundModel.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;

@Service
public class CartService {

    @Autowired
    private CartMapper mapper;

    public List<Product> selectCartById(int userid) {
        return mapper.selectCartById(userid);
    }

    public int deleteProductByArray(Map<String,Object> map) {
        return mapper.deleteProductByArray(map);
    }

    public int selectCartProductById(Map<String,Object> map) {
        return mapper.selectCartProductById(map);
    }

    public int insertCartById(Map<String, Object> map) {
        return mapper.insertCartById(map);
    }
}
