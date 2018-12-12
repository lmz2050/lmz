package com.lmz.mike.auth.service.impl;

import com.lmz.mike.auth.bean.LmzUser;
import com.lmz.mike.auth.service.LmzUserService;
import com.lmz.mike.data.support.bean.DataBean;
import com.lmz.mike.data.support.service.LService;
import org.springframework.stereotype.Service;

/**
 * 2 * @Author: Mike Lee
 * 3 * @Date: 2018/12/12 23:56
 * 4
 */
@Service
public class LmzUserServiceImpl extends LService implements LmzUserService{

    @Override
    public LmzUser getUserByUserName(String userName) {
        DataBean db = new DataBean<LmzUser>(LmzUser.class, "userName",userName);
        return en.queryObj(db);
    }

    @Override
    public LmzUser getUserByPhone(String phone) {
        DataBean db = new DataBean<LmzUser>(LmzUser.class, "phone",phone);
        return en.queryObj(db);
    }


}
