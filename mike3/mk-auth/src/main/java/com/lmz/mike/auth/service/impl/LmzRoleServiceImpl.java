package com.lmz.mike.auth.service.impl;

import com.lmz.mike.auth.bean.LmzRole;
import com.lmz.mike.auth.bean.LmzRoleUser;
import com.lmz.mike.auth.service.LmzRoleService;
import com.lmz.mike.data.support.bean.Data;
import com.lmz.mike.data.support.service.LService;
import com.lmz.mike.data.support.session.db.sql.common.Where;
import com.lmz.mike.data.support.session.db.sql.impl.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2 * @Author: Mike Lee
 * 3 * @Date: 2018/12/13 0:44
 * 4
 */
@Service
public class LmzRoleServiceImpl extends LService implements LmzRoleService {

    @Override
    public List<LmzRole> getRoleByUserId(String userId) {

        Where where = Where.instance().addCondition(" id in ( select lru.rid from "+ Data.getTab(LmzRoleUser.class)+" lru where lru.uid = ? )",userId);
        Query query = new Query(LmzRole.class,null,where,null);

        return query(query);
    }


}
