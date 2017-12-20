package com.log.dao;

import org.apache.ibatis.annotations.Param;

import com.log.model.user;

public interface userMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(user record);

    int insertSelective(user record);

    user selectByPrimaryKey(Integer id);
    
    user selectByUserId(@Param("user_string_id")String user_string_id);
    
    int getUserIndex(@Param("userStringId")String userStringId);

    int updateByPrimaryKeySelective(user record);

    int updateByPrimaryKey(user record);
}