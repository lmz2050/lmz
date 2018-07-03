package cn.lmz.mike.admin.system.dao;


import cn.lmz.mike.admin.system.vo.Lmzmenu;

public interface LmzmenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(Lmzmenu record);

    int insertSelective(Lmzmenu record);

    Lmzmenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Lmzmenu record);

    int updateByPrimaryKey(Lmzmenu record);
}