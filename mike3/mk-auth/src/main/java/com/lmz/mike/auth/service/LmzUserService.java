package com.lmz.mike.auth.service;


import com.lmz.mike.auth.bean.LmzUser;
import com.lmz.mike.data.IService;


public interface LmzUserService extends IService {

    LmzUser getUserByUserName(String userName);
    LmzUser getUserByPhone(String phone);

}
