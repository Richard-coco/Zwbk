package com.zwbk.Dao;

import com.zwbk.Entity.Zw;
import com.zwbk.Entity.ZwExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZwMapper {
    long countByExample(ZwExample example);

    int deleteByExample(ZwExample example);

    int deleteByPrimaryKey(String name);

    int insert(Zw record);

    int insertSelective(Zw record);

    List<Zw> selectByExample(ZwExample example);

    Zw selectByPrimaryKey(String name);
    
    List<Zw> selectByLocal(String local);

    int updateByExampleSelective(@Param("record") Zw record, @Param("example") ZwExample example);

    int updateByExample(@Param("record") Zw record, @Param("example") ZwExample example);

    int updateByPrimaryKeySelective(Zw record);

    int updateByPrimaryKey(Zw record);
}