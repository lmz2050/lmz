package cn.lmz.mike.admin.system.dao;


import cn.lmz.mike.admin.system.vo.Lmzadmin;

public interface LmzadminMapper {
    int deleteByPrimaryKey(String id);

    int insert(Lmzadmin record);

    int insertSelective(Lmzadmin record);

    Lmzadmin selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Lmzadmin record);

    int updateByPrimaryKey(Lmzadmin record);
}