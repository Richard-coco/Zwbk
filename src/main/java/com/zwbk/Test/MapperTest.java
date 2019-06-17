package com.zwbk.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zwbk.Dao.UserMapper;
import com.zwbk.Dao.ZwMapper;
import com.zwbk.Entity.User;
import com.zwbk.Entity.Zw;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MapperTest {
	
	@Autowired
	ZwMapper zwmapper;
	@Autowired
	UserMapper userMapper;
	
	@Test
	public void testZwMapper() {
		List<Zw>  arr = new ArrayList<>();
		arr = zwmapper.selectByLocal("亚热带");
		for (Zw zw : arr) {
			System.out.println(zw.getName());
		}
	}
	
	@Test
	public void testUserMapper() {
		User u = userMapper.selectByPrimaryKey("oGWsY56GPkCpoWGfrSUZx5b-rdKA");
		System.out.println(u);
	}
}
