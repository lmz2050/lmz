package cn.lmz.mike.admin.system.dao;

import cn.lmz.mike.admin.system.vo.Lmzweb;

public interface LmzwebMapper {
    int deleteByPrimaryKey(String id);

    int insert(Lmzweb record);

    int insertSelective(Lmzweb record);

    Lmzweb selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Lmzweb record);

    int updateByPrimaryKeyWithBLOBs(Lmzweb record);

    int updateByPrimaryKey(Lmzweb record);
}