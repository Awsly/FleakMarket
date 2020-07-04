package com.ct.service.BackgroundService;

import com.ct.mapper.BackgroundMapper.BUserMapper;
import com.ct.model.ForegroundModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BUserService {

    @Autowired
    private BUserMapper Mapper;

    public List<Map<String,Object>> selectUserList(User user) {
        return Mapper.selectUserList(user);
    }
}
