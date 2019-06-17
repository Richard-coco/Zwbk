package com.zwbk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwbk.Dao.UserMapper;
import com.zwbk.Entity.User;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public void insertAddress(User u ) {
		if(userMapper.selectByPrimaryKey(u.getOpenid()) == null) {
			userMapper.insert(u);
		}else {
			userMapper.updateByPrimaryKey(u);
		}
		
	}
	
	public User selectByPrimaryKey(String openid) {
		return userMapper.selectByPrimaryKey(openid);
	}
}
