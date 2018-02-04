package cn.lmz.mike.common.support;

import java.util.Map;

public interface IFunction {

    public Map<String, Object> getParams();
    public Object executeFun();

}
