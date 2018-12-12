package com.lmz.mike.auth.service;

import com.lmz.mike.auth.bean.LmzRole;
import com.lmz.mike.data.IService;

import java.util.List;


public interface LmzRoleService extends IService{

    List<LmzRole> getRoleByUserId(String userId);

}
