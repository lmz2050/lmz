package com.lmz.mike.auth.bean;

import lombok.Data;

/**
 * 2 * @Author: Mike Lee
 * 3 * @Date: 2018/12/3 0:21
 * 4
 */
@Data
public class LmzSystem extends BaseBean{

    private String code;
    private String name;
    private String domain;
    private String mdomain;
    private String logo;
    private String keyjson;
    private String optjson;
    private String conjson;
    private String extjson;
    private String remark;
    private Integer ord;
    private Integer active;

}
