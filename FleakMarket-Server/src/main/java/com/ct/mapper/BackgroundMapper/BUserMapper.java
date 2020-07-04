package com.ct.mapper.BackgroundMapper;

import com.ct.model.ForegroundModel.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BUserMapper {
    //查询用户信息列表
    List<Map<String,Object>> selectUserList(User user);
}
