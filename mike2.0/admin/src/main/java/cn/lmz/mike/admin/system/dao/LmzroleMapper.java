package cn.lmz.mike.admin.system.dao;

import cn.lmz.mike.admin.system.vo.Lmzrole;

public interface LmzroleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Lmzrole record);

    int insertSelective(Lmzrole record);

    Lmzrole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Lmzrole record);

    int updateByPrimaryKey(Lmzrole record);
}