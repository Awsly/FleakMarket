package com.ct.service.ForegroundService;

import com.ct.mapper.ForegroundMapper.ProductMapper;
import com.ct.model.ForegroundModel.Product;
import javafx.scene.web.PromptData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productmapper;

    public List<Product> selectByNew() {
        return productmapper.selectByNew();
    }

    public List<Product> selectByHot(int typeid) {
        return productmapper.selectByHot(typeid);
    }

    public Map<String,Object> selectProductById(int id) {
        return productmapper.selectProductById(id);
    }

    public List<Product> selectScProductById(Map<String, Object>  param) {
        return productmapper.selectScProductById(param);
    }

    public int insertProductByUser(Product product) {
        return productmapper.insertProductByUser(product);
    }

    public List<Map<String,Object>> selectCommentById(int id) {
        return productmapper.selectCommentById(id);
    }

    public int insertProductToLove(Map<String, Object> param) {
        return productmapper.insertProductToLove(param);
    }

    public int selectLoveById(Map<String, Object> param){
        return productmapper.selectLoveById(param);
    }

    public int insertComment(Map<String, Object> param){
        return productmapper.insertComment(param);
    }

    public List<Product> selectMyCollectById(int id){
        return productmapper.selectMyCollectById(id);
    }

    public List<Product> selectMyProductById(int id){
        return productmapper.selectMyProductById(id);
    }
    public int updateProductByUser(Product product){
        return productmapper.updateProductByUser(product);
    }

    public int deleteProductById(int id) {
        return productmapper.deleteProductById(id);
    }

    public int deleteProductToLove(Map<String, Object> param) {
        return productmapper.deleteProductToLove(param);
    }
}
