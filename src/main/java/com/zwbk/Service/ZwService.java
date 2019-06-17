package com.zwbk.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwbk.Dao.ZwMapper;
import com.zwbk.Entity.Zw;

@Service
public class ZwService {
	
	@Autowired
	ZwMapper zwMapper;
	
	public List<Zw> selectByLocal(String local){
		return zwMapper.selectByLocal(local);
	}
}
